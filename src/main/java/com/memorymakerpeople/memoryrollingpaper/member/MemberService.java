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
/*
    public GetLogoutRes logout(UserLoginRes loginRes) {

//        Member findMember = memberRepository.findByEmail(loginRes.getEmail());
//        String kakaoId = findMember.getKakaoId();
//        String kakaoAccessToken = findMember.getKakaoAccessToken();
        *//*log.info("kakaoId = {}", kakaoId);
        log.info("kakaoAccessToken = {}", kakaoAccessToken);
*//*
        //header
        HttpHeaders headers = new HttpHeaders();
        //headers.add("Authorization", "Bearer " + kakaoAccessToken);

        //parameter
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("target_id_type", "user_id");
//        params.add("target_id", kakaoId);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
        RestTemplate rt = new RestTemplate();

        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/logout?client_id="+client_id+"&logout_redirect_uri="+logout_uri,
                //"https://kauth.kakao.com/oauth/logout?client_id=" + client_id + "&logout_redirect_uri=" + logout_uri, //{요청할 서버 주소}
                //"https://kauth.kakao.com/v1/user/logout", //{요청할 서버 주소}
                HttpMethod.GET, //{요청할 방식}
                entity, // {요청할 때 보낼 데이터}
                String.class);

        HttpStatus statusCode = response.getStatusCode();
        if (statusCode.is5xxServerError()) {
            return new GetLogoutRes("요청 실패 카카오 서버 오류", BaseResponseStatus.FAIL);
        }
        if (!statusCode.is3xxRedirection()) {
            return new GetLogoutRes("요청 실패", BaseResponseStatus.FAIL);
        }
        return new GetLogoutRes(logout_uri, BaseResponseStatus.SUCCESS);
    }*/
}
