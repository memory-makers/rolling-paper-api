package com.memorymakerpeople.memoryrollingpaper.member;

import com.memorymakerpeople.memoryrollingpaper.authLogin.UserLoginRes;
import com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus;
import com.memorymakerpeople.memoryrollingpaper.member.model.Member;
import com.memorymakerpeople.memoryrollingpaper.member.model.PutMemberRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    /*//회원가입
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
    }*/

    public PutMemberRes updateNickname(UserLoginRes userLoginRes, String nickname) {
        Member member = memberRepository.findByEmail(userLoginRes.getEmail());
        member.setNickname(nickname);
        return new PutMemberRes(memberRepository.save(member), BaseResponseStatus.SUCCESS);
    }
}
