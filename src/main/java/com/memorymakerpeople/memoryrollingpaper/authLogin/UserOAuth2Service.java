package com.memorymakerpeople.memoryrollingpaper.authLogin;


import com.memorymakerpeople.memoryrollingpaper.member.MemberDao;
import com.memorymakerpeople.memoryrollingpaper.member.model.Member;
import com.memorymakerpeople.memoryrollingpaper.member.MemberRepository;
import com.memorymakerpeople.memoryrollingpaper.member.model.PostMemberReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service

public class UserOAuth2Service extends DefaultOAuth2UserService {
    private final MemberDao memberDao;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();

        log.info("attributes :: " + attributes);

//        동의철회시 필요한 AccessToken 값.
        System.out.println("getAccessToken: "+userRequest.getAccessToken().getTokenValue());

        Map<String, Object> kakao_account = (Map<String, Object>) attributes.get("kakao_account");
        System.out.println("kakao_account!!!! = " + kakao_account);
        String email = (String) kakao_account.get("email");
        System.out.println("email = " + email);

        String kakao_id = attributes.get("id").toString();
        System.out.println("id = " + kakao_id);

        Member member = Member.builder()
                .email(email)
                .username(kakao_id) // 여기 수정해야됨.
                .build();
        log.info("member :: " + member);
        if (memberDao.checkEmail(email) == 0) {
            memberDao.createMember(new PostMemberReq(kakao_id, email));
        } else {
            System.out.println("가입한적 있음.");
        }
        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority("ROLE_MEMBER")), attributes, "id");
    }

}