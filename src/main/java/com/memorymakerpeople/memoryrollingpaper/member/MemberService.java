package com.memorymakerpeople.memoryrollingpaper.member;

import com.memorymakerpeople.memoryrollingpaper.authLogin.UserLoginRes;
import com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus;
import com.memorymakerpeople.memoryrollingpaper.exception.CustomException;
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
import java.util.Optional;

import static com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    @Value("${kakao.client_id}")
    private String client_id;

    //리펙토링 필요
    public PutMemberRes updateNickname(UserLoginRes userLoginRes, String nickname) {
        Optional<Member> member = memberRepository.findByEmail(userLoginRes.getEmail());

        if(member.isEmpty()) {
            throw new CustomException(FOUND_USER_INFO_NULL);
        }

        member.get().setNickname(nickname);
        Member save = memberRepository.save(member.get());

        return new PutMemberRes(save.getId());
    }

    //리펙토링 필요
    public String selectNickname(String email) {
        Optional<Member> optionalMember = memberRepository.findByEmail(email);

        if(optionalMember.isEmpty()) {
            throw new CustomException(FOUND_USER_INFO_NULL);
        }
        return optionalMember.get().getNickname();
    }

}
