package com.memorymakerpeople.memoryrollingpaper.paper;

import com.memorymakerpeople.memoryrollingpaper.authLogin.UserLoginRes;
import com.memorymakerpeople.memoryrollingpaper.config.BaseResponse;
import com.memorymakerpeople.memoryrollingpaper.paper.model.Paper;
import com.memorymakerpeople.memoryrollingpaper.paper.model.postPaperResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/paper")
@Api(tags = {"롤링페이퍼 관리 API"})
public class PaperController {

    @Autowired
    private PaperService paperService;

    @PostMapping
    @ApiOperation(value = "롤링페이퍼 생성", notes = "현재 로그인된 아이디를 기준으로 롤링페이퍼를 생성 합니다.")
    public BaseResponse<postPaperResponse>  createPaper(@RequestBody Paper paper, @AuthenticationPrincipal UserLoginRes userLoginRes){
        postPaperResponse result = paperService.createPaper(paper, userLoginRes.getId());

        return new BaseResponse<>(result);
    }

    @GetMapping
    @ApiOperation(value = "롤링페이퍼 목록", notes = "현재 로그인된 아이디를 기준으로 생성된 롤링페이퍼를 조회합니다.")
    public BaseResponse<List<Paper>> paperList(@AuthenticationPrincipal UserLoginRes userLoginRes){
        return new BaseResponse<>(paperService.selectPaper(userLoginRes.getId()));
    }

    @GetMapping("/{paperId}")
    @ApiOperation(value = "롤링페이퍼 보기", notes = "하나의 롤링페이퍼를 조회합니다.")
    public BaseResponse<PaperResponseDto> paperDetail(PaperRequestDto paperRequestDto, HttpServletRequest request, HttpServletResponse response){
        return paperService.selectOnePaper(paperRequestDto);
    }

    @PutMapping("update")
    @ApiOperation(value = "롤링페이퍼 수정", notes = "롤링페이퍼를 수정합니다.")
    public BaseResponse<PaperResponseDto> updatePaper(@RequestBody Paper paper, HttpServletRequest request, HttpServletResponse response){
        return paperService.updatePaper(paper);
    }

    @PutMapping("delete")
    @ApiOperation(value = "롤링페이퍼 삭제", notes = "롤링페이퍼를 삭제합니다.")
    public BaseResponse<PaperResponseDto> deletePaper(@RequestBody Paper paper, HttpServletRequest request, HttpServletResponse response){
        return paperService.deletePaper(paper);
    }
}
