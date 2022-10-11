package com.memorymakerpeople.memoryrollingpaper.authLogin;


import com.memorymakerpeople.memoryrollingpaper.member.MemberDao;
import com.memorymakerpeople.memoryrollingpaper.member.model.Member;
import com.memorymakerpeople.memoryrollingpaper.member.MemberRepository;
import com.memorymakerpeople.memoryrollingpaper.member.model.PostMemberReq;
import com.memorymakerpeople.memoryrollingpaper.member.model.PostMemberRes;
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
    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();

        log.info("attributes :: " + attributes);

//        동의철회시 필요한 AccessToken 값.
        String kakao_AT = userRequest.getAccessToken().getTokenValue();

        String kakao_Id = oAuth2User.getAttribute("id").toString();
        log.info("================ ID와 토큰 ================");
        log.info("getAccessToken = {} ", kakao_AT);
        log.info("kakao user Id = {}", kakao_Id);
        log.info("================ ID와 토큰 ================");

        Map<String, Object> kakao_account = (Map<String, Object>) attributes.get("kakao_account");
        String email = (String) kakao_account.get("email");

        if (memberDao.checkEmail(email) == 0) {
            PostMemberRes member = memberDao.createMember(new PostMemberReq(email));

        } else {
            log.info("가입된 유저");
        }

        Member findMember = memberRepository.findByEmail(email);
        findMember.setKakaoId(kakao_Id);
        findMember.setKakaoAccessToken(kakao_AT);
        Member save = memberRepository.save(findMember);
        log.info("로그인 완료 = {}", save);

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority("ROLE_MEMBER")), attributes, "id");
    }

}