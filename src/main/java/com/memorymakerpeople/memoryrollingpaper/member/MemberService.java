package com.memorymakerpeople.memoryrollingpaper.member;

import com.memorymakerpeople.memoryrollingpaper.authLogin.UserLoginRes;
import com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus;
import com.memorymakerpeople.memoryrollingpaper.member.model.GetLogoutRes;
import com.memorymakerpeople.memoryrollingpaper.member.model.Member;
import com.memorymakerpeople.memoryrollingpaper.member.model.PutMemberRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;


@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    @Value("${kakao.client_id}")
    private String client_id;

    //리펙토링 필요
    public PutMemberRes updateNickname(UserLoginRes userLoginRes, String nickname) {
        Member member = memberRepository.findByEmail(userLoginRes.getEmail());
        member.setNickname(nickname);
        return new PutMemberRes(memberRepository.save(member), BaseResponseStatus.SUCCESS);
    }

    //리펙토링 필요
    public String selectNickname(String email) {
        Member Member = memberRepository.findByEmail(email);
        if(!ObjectUtils.isEmpty(Member)) {
            return Member.getNickname();
        }

        return null;
    }

}
