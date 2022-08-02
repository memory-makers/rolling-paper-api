package com.memorymakerpeople.memoryrollingpaper.controller;

import com.memorymakerpeople.memoryrollingpaper.domain.Member;
import com.memorymakerpeople.memoryrollingpaper.dto.DefaultResponseDto;
import com.memorymakerpeople.memoryrollingpaper.dto.MemberRequestDto;
import com.memorymakerpeople.memoryrollingpaper.dto.MemberResponseDto;
import com.memorymakerpeople.memoryrollingpaper.dto.PaperResponseDto;
import com.memorymakerpeople.memoryrollingpaper.service.MemberService;
import com.memorymakerpeople.memoryrollingpaper.util.SessionConstants;
import com.memorymakerpeople.memoryrollingpaper.util.SessionUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@Api(tags = {"회원관리 API"})
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "카카오 회원 로그인/회원가입", notes = "카카오에서 발급하는 id를 사용해 로그인을 합니다. 만약 기존 회원이 아니라면 추가(회원가입) 합니다.")
    @PostMapping("/login")
    public MemberResponseDto register(@RequestBody MemberRequestDto memberRequestDto, HttpServletRequest request, HttpServletResponse response) {
        MemberResponseDto result = memberService.joinUser(memberRequestDto);
        if (result.message == "register" && result.statusCode == "complete") {
            result = memberService.joinUser(memberRequestDto);
        }
        if (result.statusCode == "complete") {
            // 로그인 성공 처리
            HttpSession session = request.getSession();                         // 세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성하여 반환
            session.setAttribute(SessionConstants.LOGIN_MEMBER, result.getMember());   // 세션에 로그인 회원 정보 보관
            session.setMaxInactiveInterval(1800); // 1800초
            response.setStatus(HttpStatus.OK.value());
        }
        return result;
    }

    @ApiOperation(value = "로그아웃", notes = "세션 할당 해제. <awt 토큰 적용 예정>")
    @PostMapping("/logout")
    public DefaultResponseDto logout(HttpServletRequest request) {
        DefaultResponseDto result = new DefaultResponseDto();

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();   // 세션 날림
            result.statusCode = "complete";
            result.message = "logout";
        }

        return result;
    }

    @ApiOperation(value = "닉네임 설정", notes = "닉네임 설정, username이나 id중에 식별자와 변경할 nickname을 파라미터로 전달")
    @PutMapping
    public MemberResponseDto setNickname(HttpServletRequest request,@RequestBody Member memberRequestDto) {
        System.out.println("MemberController.setNickname");
        System.out.println("request = " + request);
        System.out.println("SessionMember = " + SessionUtils.GetSessionMember(request));
        String loginId = SessionUtils.GetLoginId(request);
        if (loginId.isEmpty()){
            MemberResponseDto result = new MemberResponseDto();
            result.statusCode = "fail";
            result.message = "not found user";
            return result;
        }
        Member sessionMember = SessionUtils.GetSessionMember(request);
        sessionMember.setNickname(memberRequestDto.getNickname());
        MemberResponseDto memberResponseDto = memberService.updateNickname(sessionMember);

        if(memberResponseDto.statusCode.equals("complete")) { //닉네임 변경 완료시 session에 담긴 login member도 update
            HttpSession session = request.getSession();                         // 세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성하여 반환
            session.setAttribute(SessionConstants.LOGIN_MEMBER, memberResponseDto.getMember());   // 세션에 로그인 회원 정보 보관
            session.setMaxInactiveInterval(1800); // 1800초
        }
        return memberResponseDto;
    }
}
