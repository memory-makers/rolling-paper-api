package com.memorymakerpeople.memoryrollingpaper.utils.oauth2;

import com.memorymakerpeople.memoryrollingpaper.authLogin.UserLoginRes;
import com.memorymakerpeople.memoryrollingpaper.config.JwtTokenUtil;
import com.memorymakerpeople.memoryrollingpaper.member.MemberProvider;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    private final MemberProvider memberProvider;

    @Autowired
    private final JwtTokenUtil jwtTokenUtil;

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
        String clientIp = getClientIp(req);
        /*return UriComponentsBuilder.fromUriString("https://rolling-paper-makers.vercel.app/kakao?token="+token)
                .build().toUriString();*/
        return UriComponentsBuilder.fromUriString("http://localhost:5173/kakao?token="+token)
                .build().toUriString();
        /*System.out.println("=========OAuth2AuthenticationSuccessHandler.makeRedirectUrl ============");
        System.out.println("http://"+clientIp+"/kakao?token="+token);
        return UriComponentsBuilder.fromUriString(clientIp+"/kakao?token="+token)
                .build().toUriString();*/
    }

    public static String getClientIp(HttpServletRequest req) {
        String ip = req.getHeader("X-FORWARDED-FOR");

        //proxy 환경일 경우
        if (ip == null || ip.length() == 0) {
            System.out.println("11111111111111111");
            ip = req.getHeader("Proxy-Client-IP");
        }

        //웹로직 서버일 경우
        if (ip == null || ip.length() == 0) {
            System.out.println("22222222222222222");
            ip = req.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0) {
            System.out.println("33333333333333333");
            ip = req.getRemoteAddr() ;
        }

        return ip;
    }
}