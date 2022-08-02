package com.memorymakerpeople.memoryrollingpaper.service;

import com.memorymakerpeople.memoryrollingpaper.domain.Member;
import com.memorymakerpeople.memoryrollingpaper.dto.DefaultResponseDto;
import com.memorymakerpeople.memoryrollingpaper.dto.MemberRequestDto;
import com.memorymakerpeople.memoryrollingpaper.dto.MemberResponseDto;
import com.memorymakerpeople.memoryrollingpaper.repository.MemberRepository;
import com.memorymakerpeople.memoryrollingpaper.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    //회원가입
    public MemberResponseDto joinUser(MemberRequestDto memberRequest) {
        Member member = new Member();
        MemberResponseDto result = new MemberResponseDto();
        //회원가입 여부 확인
        Optional<Member> user = isUser(memberRequest);
        if(user.isPresent()) {
            result.setMember(user.get());
            //유저면 토큰 확인 후 토큰 발급
            result.statusCode = "complete";
            result.message = "login";
        }else{
            //회원 가입 후 토큰 발급
            member.setUsername(memberRequest.getUsername());
            result.setMember(memberRepository.save(member));
            if (result.getMember().getUsername().isEmpty()){
                result.statusCode = "fail";
            }else{
                result.statusCode = "complete";
            }
            result.message = "register";
        }
        return result;
    }

    public Optional<Member> isUser(MemberRequestDto memberRequest){
        return memberRepository.findByUsername(memberRequest.getUsername());
    }

    public MemberResponseDto updateNickname(Member memberRequestDto) {
        MemberResponseDto result = new MemberResponseDto();
        result.setMember(memberRepository.save(memberRequestDto));
        if (result.getMember().getNickname() == null){
            result.statusCode = "fail";
        }else{
            result.statusCode = "complete";
        }
        result.message = "nickname update";
        return  result;
    }
}
