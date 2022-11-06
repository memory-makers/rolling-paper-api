package com.memorymakerpeople.memoryrollingpaper.authLogin;


import com.memorymakerpeople.memoryrollingpaper.member.MemberDao;
import com.memorymakerpeople.memoryrollingpaper.member.model.Member;
import com.memorymakerpeople.memoryrollingpaper.member.MemberRepository;
import com.memorymakerpeople.memoryrollingpaper.member.model.PostMemberReq;
import com.memorymakerpeople.memoryrollingpaper.member.model.PostMemberRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

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

        log.debug("attributes :: " + attributes);

//        동의철회시 필요한 AccessToken 값.
        String kakao_AT = userRequest.getAccessToken().getTokenValue();

        String kakao_Id = oAuth2User.getAttribute("id").toString();
        log.debug("================ ID와 토큰 ================");
        log.debug("getAccessToken = {} ", kakao_AT);
        log.debug("kakao user Id = {}", kakao_Id);
        log.debug("================ ID와 토큰 ================");

        Map<String, Object> kakao_account = (Map<String, Object>) attributes.get("kakao_account");
        String email = (String) kakao_account.get("email");

        if (memberDao.checkEmail(email) == 0) {
            PostMemberRes member = memberDao.createMember(new PostMemberReq(email));

        } else {
            log.debug("가입된 유저");
        }

        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        if(optionalMember.isEmpty()) {
            throw new OAuth2AuthenticationException("OAuth2 Error");
        }
        Member findMember = optionalMember.get();
        findMember.setKakaoId(kakao_Id);
        findMember.setKakaoAccessToken(kakao_AT);
        Member save = memberRepository.save(findMember);
        log.debug("로그인 완료 = {}", save);

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority("ROLE_MEMBER")), attributes, "id");
    }

}