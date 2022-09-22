package com.memorymakerpeople.memoryrollingpaper.utils.oauth2;

import com.memorymakerpeople.memoryrollingpaper.authLogin.UserLoginRes;
import com.memorymakerpeople.memoryrollingpaper.config.JwtTokenUtil;
import com.memorymakerpeople.memoryrollingpaper.member.MemberProvider;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final MemberProvider memberProvider;

    @Autowired
    public OAuth2AuthenticationSuccessHandler(MemberProvider memberProvider){
        this.memberProvider = memberProvider;
    }

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        //login 성공한 사용자 목록.
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        Map<String, Object> kakao_account = (Map<String, Object>) oAuth2User.getAttributes().get("kakao_account");
        String email = (String) kakao_account.get("email");
        UserLoginRes userLoginRes = memberProvider.findByEmail(email);
        log.info("userLoginRes!!-!! = {} and Email = {}", userLoginRes, userLoginRes.getEmail());

        //토큰 생성
        String jwt = jwtTokenUtil.generateToken(userLoginRes);
        logger.info("jwt :: " + jwt);
        String url = makeRedirectUrl(jwt, request);

        if (response.isCommitted()) {
            logger.debug("응답이 이미 커밋된 상태입니다. " + url + "로 리다이렉트하도록 바꿀 수 없습니다.");
            return;
        }
        getRedirectStrategy().sendRedirect(request, response, url);
    }


    private String makeRedirectUrl(String token, HttpServletRequest req) {
        /*return UriComponentsBuilder.fromUriString("http://www.alittlevanilla.kro.kr/oauth2/redirect/"+token)
                .build().toUriString();*/
        /*return UriComponentsBuilder.fromUriString("https://rolling-pager-client.vercel.app/kakao?token="+token)
                .build().toUriString();*/
        String clientIp = getClientIp(req);
        return UriComponentsBuilder.fromUriString("http://localhost:5173/kakao?token="+token)
                .build().toUriString();
    }

    public static String getClientIp(HttpServletRequest req) {
        String ip = req.getHeader("X-Forwarded-For");
        if (ip == null) ip = req.getRemoteAddr();
        return ip;
    }
}