package com.memorymakerpeople.memoryrollingpaper.utils.oauth2;

import com.memorymakerpeople.memoryrollingpaper.authLogin.UserLoginRes;
import com.memorymakerpeople.memoryrollingpaper.config.JwtTokenUtil;
import com.memorymakerpeople.memoryrollingpaper.member.MemberDao;
import com.memorymakerpeople.memoryrollingpaper.member.MemberProvider;
import com.memorymakerpeople.memoryrollingpaper.member.model.UserLoginResWithStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final MemberProvider memberProvider;

    @Autowired
    public OAuth2AuthenticationSuccessHandler(MemberProvider memberProvider){
        this.memberProvider = memberProvider;
    }

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    MemberDao memberDao;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

//        login 성공한 사용자 목록.
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        Map<String, Object> kakao_account = (Map<String, Object>) oAuth2User.getAttributes().get("kakao_account");
        String email = (String) kakao_account.get("email");
        UserLoginRes userLoginRes = memberProvider.findByEmail(email);

        //여기 뭐임? 수정좀 해야할듯
        if(userLoginRes.getId() == null) {
            getRedirectStrategy().sendRedirect(request, response, UriComponentsBuilder.fromUriString("http://www.서버주소.co.kr/leavemember")
                    .build().toUriString());
        }

        String jwt = jwtTokenUtil.generateToken(userLoginRes);
        logger.info("jwt :: " + jwt);
        String url = makeRedirectUrl(jwt);
        System.out.println("url: " + url);

        if (response.isCommitted()) {
            logger.debug("응답이 이미 커밋된 상태입니다. " + url + "로 리다이렉트하도록 바꿀 수 없습니다.");
            return;
        }
        getRedirectStrategy().sendRedirect(request, response, url);
    }


    private String makeRedirectUrl(String token) {
        /*return UriComponentsBuilder.fromUriString("http://www.alittlevanilla.kro.kr/oauth2/redirect/"+token)
                .build().toUriString();*/
        return UriComponentsBuilder.fromUriString("http://www.서버주소.co.kr/oauth2/redirect/"+token)
                .build().toUriString();
    }
}