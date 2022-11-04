package com.memorymakerpeople.memoryrollingpaper.paper;

import com.memorymakerpeople.memoryrollingpaper.authLogin.UserLoginRes;
import com.memorymakerpeople.memoryrollingpaper.config.BaseResponse;
import com.memorymakerpeople.memoryrollingpaper.paper.model.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/papers")
@Api(tags = {"롤링페이퍼 관리 API"})
@Slf4j
public class PaperController {

    private final PaperService paperService;

    @PostMapping
    @ApiOperation(value = "롤링페이퍼 생성", notes = "현재 로그인된 아이디를 기준으로 롤링페이퍼를 생성 합니다.")
    public BaseResponse<PostPaperRes> createPaper(@RequestBody @Validated PostPaperReq postPaperReq, @AuthenticationPrincipal UserLoginRes userLoginRes){
        log.debug("userLoginRes = {}", userLoginRes);
        PostPaperRes result = paperService.createPaper(postPaperReq, userLoginRes.getEmail());
        return new BaseResponse<>(result);
    }

    @GetMapping
    @ApiOperation(value = "롤링페이퍼 목록", notes = "현재 로그인된 아이디를 기준으로 생성된 롤링페이퍼를 조회합니다.")
    public BaseResponse<List<GetPaperRes>> paperList(@AuthenticationPrincipal UserLoginRes userLoginRes){
        log.debug("userLoginRes = {}", userLoginRes);
        List<GetPaperRes> result = paperService.selectPaper(userLoginRes.getEmail());
        return new BaseResponse<>(result);
    }

    @GetMapping("/{paperId}")
    @ApiOperation(value = "롤링페이퍼 보기", notes = "하나의 롤링페이퍼를 조회합니다.")
    public BaseResponse<GetPaperRes> paperDetail(@PathVariable Long paperId){
        log.debug("paperId = {}", paperId);
        GetPaperRes result = paperService.selectOnePaper(paperId);
        return new BaseResponse<>(result);
    }

    @PutMapping()
    @ApiOperation(value = "롤링페이퍼 수정", notes = "롤링페이퍼를 수정합니다.")
    public BaseResponse<PutPaperRes> updatePaper(@RequestBody @Validated PutPaperReq putPaperReq, @AuthenticationPrincipal UserLoginRes userLoginRes){
        log.debug("paper = {}", putPaperReq);
        PutPaperRes result = paperService.updatePaper(putPaperReq, userLoginRes.getEmail());
        return new BaseResponse<>(result);
    }

    @GetMapping("/url")
    @ApiOperation(value = "paperId 조회", notes = "url을 이용해서 paperId를 조회합니다.")
    public BaseResponse<GetpaperIdRes> findPaperId(@RequestParam String paperUrl){
        log.debug("paperUrl = {}", paperUrl);
        GetpaperIdRes result = paperService.selectPaperId(paperUrl);
        return new BaseResponse<>(result);
    }

    @DeleteMapping("/{paperId}")
    @ApiOperation(value = "롤링페이퍼 삭제", notes = "롤링페이퍼를 삭제합니다.")
    public BaseResponse<DeletePaperRes> deletePaper(@AuthenticationPrincipal UserLoginRes userLoginRes, @PathVariable Long paperId){
        log.debug("userLoginRes = {}", userLoginRes);
        log.debug("paperId = {}", paperId);
        DeletePaperRes result = paperService.deletePaper(paperId, userLoginRes.getEmail());
        return new BaseResponse<>(result);
    }

    @GetMapping("/{paperId}/nickname")
    @ApiOperation(value = "nickname 조회", notes = "paperId를 이용해서 nickname을 조회합니다.")
    public BaseResponse<GetPaperNicknameRes> findNickname(@PathVariable Long paperId){
        log.debug("paperId = {}", paperId);
        GetPaperNicknameRes result = paperService.selectNickname(paperId);
        return new BaseResponse<>(result);
    }
}
