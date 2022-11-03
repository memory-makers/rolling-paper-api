package com.memorymakerpeople.memoryrollingpaper.member;

import com.memorymakerpeople.memoryrollingpaper.authLogin.UserLoginRes;
import com.memorymakerpeople.memoryrollingpaper.config.BaseResponse;
import com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus;
import com.memorymakerpeople.memoryrollingpaper.exception.CustomException;
import com.memorymakerpeople.memoryrollingpaper.member.model.GetLogoutRes;
import com.memorymakerpeople.memoryrollingpaper.member.model.GetMemberRes;
import com.memorymakerpeople.memoryrollingpaper.member.model.PutMemberReq;
import com.memorymakerpeople.memoryrollingpaper.member.model.PutMemberRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

import static com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus.*;

@RestController
@RequiredArgsConstructor
@Api(tags = {"회원관리 API"})
@RequestMapping("/members")
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @ApiOperation(value = "사용자 정보 조회", notes = "현재 로그인된 사용자의 정보를 불러옵니다.")
    @GetMapping
    public BaseResponse<GetMemberRes> getLoginUser(@AuthenticationPrincipal UserLoginRes userLoginRes) {
        log.debug("UserLoginRes = {}", userLoginRes);
        if (ObjectUtils.isEmpty(userLoginRes)) {
            throw new CustomException(GET_USER_INFO_NULL);
        }
        return new BaseResponse<GetMemberRes>(userLoginRes);
    }

    @ApiOperation(value = "닉네임 설정", notes = "닉네임 설정, username이나 id중에 식별자와 변경할 nickname을 파라미터로 전달")
    @PutMapping
    public BaseResponse<PutMemberRes> setNickname(@AuthenticationPrincipal UserLoginRes userLoginRes, @RequestBody PutMemberReq memberReq) {
        log.debug("UserLoginRes = {}", userLoginRes);
        log.debug("memberReq = {}", memberReq);
        return new BaseResponse<>(memberService.updateNickname(userLoginRes, memberReq.getNickname()));
    }

    @ApiOperation(value="사용자 닉네임 조회", notes = "현재 접속한 사람의 사용자 닉네임 조회을 조회합니다.")
    @GetMapping("/nickname")
    public String getNickname(@AuthenticationPrincipal UserLoginRes loginRes) {
        log.debug("loginRes = {}", loginRes);
        return memberService.selectNickname(loginRes.getEmail());
    }

}
