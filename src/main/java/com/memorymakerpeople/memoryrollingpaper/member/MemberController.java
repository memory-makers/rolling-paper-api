package com.memorymakerpeople.memoryrollingpaper.member;

import com.memorymakerpeople.memoryrollingpaper.authLogin.UserLoginRes;
import com.memorymakerpeople.memoryrollingpaper.config.BaseResponse;
import com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus;
import com.memorymakerpeople.memoryrollingpaper.member.model.GetMemberRes;
import com.memorymakerpeople.memoryrollingpaper.member.model.PutMemberReq;
import com.memorymakerpeople.memoryrollingpaper.member.model.PutMemberRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = {"회원관리 API"})
@RequestMapping("/members")
@Slf4j
public class MemberController {

    @Autowired
    private MemberService memberService;

    /*@ApiOperation(value = "카카오 회원 로그인/회원가입", notes = "카카오에서 발급하는 id를 사용해 로그인을 합니다. 만약 기존 회원이 아니라면 추가(회원가입) 합니다.")
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
    }*/

    @ApiOperation(value = "사용자 정보 조회", notes = "현재 로그인된 사용자의 정보를 불러옵니다.")
    @GetMapping
    public BaseResponse<GetMemberRes> getLoginUser(@AuthenticationPrincipal UserLoginRes userLoginRes) {
        log.info("UserLoginRes = {}", userLoginRes);
        if (ObjectUtils.isEmpty(userLoginRes)) {
            return new BaseResponse<GetMemberRes>(null, BaseResponseStatus.GET_USER_INFO_NULL);
        }
        return new BaseResponse<GetMemberRes>(userLoginRes, BaseResponseStatus.SUCCESS);
    }

    @ApiOperation(value = "닉네임 설정", notes = "닉네임 설정, username이나 id중에 식별자와 변경할 nickname을 파라미터로 전달")
    @PutMapping
    public BaseResponse<PutMemberRes> setNickname(@AuthenticationPrincipal UserLoginRes userLoginRes, @RequestBody PutMemberReq memberReq) {
        System.out.println("userLoginRes = " + userLoginRes);
        System.out.println("memberReq = " + memberReq);
        return new BaseResponse<>(memberService.updateNickname(userLoginRes, memberReq.getNickname()));
    }

    @ApiOperation(value="사용자 닉네임 조회(레몬 전용)", notes = "레몬을 위해 작성한 사용자 닉네임 조회입니다.")
    @GetMapping("/nickname")
    public String getNickname(@AuthenticationPrincipal UserLoginRes loginRes) {
        System.out.println("loginRes = " + loginRes);
        return memberService.selectNickname(loginRes.getEmail());
    }
}
