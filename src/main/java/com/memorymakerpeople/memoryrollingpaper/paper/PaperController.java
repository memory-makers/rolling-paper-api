package com.memorymakerpeople.memoryrollingpaper.paper;

import com.memorymakerpeople.memoryrollingpaper.authLogin.UserLoginRes;
import com.memorymakerpeople.memoryrollingpaper.config.BaseResponse;
import com.memorymakerpeople.memoryrollingpaper.paper.model.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/papers")
@Api(tags = {"롤링페이퍼 관리 API"})
@Slf4j
public class PaperController {

    @Autowired
    private PaperService paperService;

    @PostMapping
    @ApiOperation(value = "롤링페이퍼 생성", notes = "현재 로그인된 아이디를 기준으로 롤링페이퍼를 생성 합니다.")
    public BaseResponse<PostPaperRes> createPaper(@RequestBody PostPaperReq postPaperReq, @AuthenticationPrincipal UserLoginRes userLoginRes){
        log.info("userLoginRes = {}", userLoginRes);
        return new BaseResponse<>(paperService.createPaper(postPaperReq, userLoginRes.getEmail()));
    }

    @GetMapping
    @ApiOperation(value = "롤링페이퍼 목록", notes = "현재 로그인된 아이디를 기준으로 생성된 롤링페이퍼를 조회합니다.")
    public BaseResponse<List<Paper>> paperList(@AuthenticationPrincipal UserLoginRes userLoginRes){
        log.info("userLoginRes = {}", userLoginRes);
        return new BaseResponse<>(paperService.selectPaper(userLoginRes.getEmail()));
    }

    @GetMapping("/{paperId}")
    @ApiOperation(value = "롤링페이퍼 보기", notes = "하나의 롤링페이퍼를 조회합니다.")
    public BaseResponse<GetPaperRes> paperDetail(@PathVariable BigInteger paperId){
        log.info("paperId = {}", paperId);
        return new BaseResponse<>(paperService.selectOnePaper(paperId));
    }

    @PutMapping("/update")
    @ApiOperation(value = "롤링페이퍼 수정", notes = "롤링페이퍼를 수정합니다.")
    public BaseResponse<PutPaperRes> updatePaper(@RequestBody PutPaperReq putPaperReq, @AuthenticationPrincipal UserLoginRes userLoginRes){
        log.info("paper = {}", putPaperReq);
        return new BaseResponse<>(paperService.updatePaper(putPaperReq, userLoginRes.getEmail()));
    }
    /*
    @PutMapping("/update/{paperId}")
    @ApiOperation(value = "롤링페이퍼 삭제", notes = "롤링페이퍼를 삭제합니다.")
    public BaseResponse<PaperResponseDto> deletePaper(@RequestBody Paper paper, HttpServletRequest request, HttpServletResponse response){
        return paperService.deletePaper(paper);
    }*/
}
