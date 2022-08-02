package com.memorymakerpeople.memoryrollingpaper.controller;

import com.memorymakerpeople.memoryrollingpaper.domain.Paper;
import com.memorymakerpeople.memoryrollingpaper.dto.PaperRequestDto;
import com.memorymakerpeople.memoryrollingpaper.dto.PaperResponseDto;
import com.memorymakerpeople.memoryrollingpaper.service.PaperService;
import com.memorymakerpeople.memoryrollingpaper.util.SessionUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/paper")
@Api(tags = {"롤링페이퍼 관리 API"})
public class PaperController {

    @Autowired
    private PaperService paperService;

    @PostMapping
    @ApiOperation(value = "롤링페이퍼 생성", notes = "현재 로그인된 아이디를 기준으로 롤링페이퍼를 생성 합니다.")
    public PaperResponseDto createPaper(@RequestBody Paper paper, HttpServletRequest request, HttpServletResponse response){
        PaperResponseDto result = new PaperResponseDto();
        String loginId = SessionUtils.GetLoginId(request);
        if (loginId.isEmpty()){
            result.statusCode = "fail";
            result.message = "not found user";
        }else {
            String uuid = UUID.randomUUID().toString();
            paper.setPaperUrl(uuid);
            paper.setUserId(loginId);
            result.setPaperUrl(uuid);
            result = paperService.createPaper(paper);
        }

        return result;
    }

    @GetMapping
    @ApiOperation(value = "롤링페이퍼 목록", notes = "현재 로그인된 아이디를 기준으로 생성된 롤링페이퍼를 조회합니다.")
    public List<Paper> paperList(HttpServletRequest request, HttpServletResponse response){
        String loginId = SessionUtils.GetLoginId(request);
        if (loginId.isEmpty()){
            return null;
        }
        Paper paper = new Paper();
        paper.setUserId(loginId);
        return paperService.selectPaper(paper);
    }

    @GetMapping("/{paperId}")
    @ApiOperation(value = "롤링페이퍼 보기", notes = "하나의 롤링페이퍼를 조회합니다.")
    public PaperResponseDto paperDetail(PaperRequestDto paperRequestDto, HttpServletRequest request, HttpServletResponse response){
        String loginId = SessionUtils.GetLoginId(request);
        if (loginId.isEmpty()){
            return null;
        }
        paperRequestDto.setUserId(loginId);
        return paperService.selectOnePaper(paperRequestDto);
    }

    @PutMapping("update")
    @ApiOperation(value = "롤링페이퍼 수정", notes = "롤링페이퍼를 수정합니다.")
    public PaperResponseDto updatePaper(@RequestBody Paper paper, HttpServletRequest request, HttpServletResponse response){
        PaperResponseDto result = new PaperResponseDto();
        String loginId = SessionUtils.GetLoginId(request);
        if (loginId.isEmpty()){
            result.statusCode = "fail";
            result.message = "not found user";
        }

        return paperService.updatePaper(paper);
    }

    @PutMapping("delete")
    @ApiOperation(value = "롤링페이퍼 삭제", notes = "롤링페이퍼를 삭제합니다.")
    public PaperResponseDto deletePaper(@RequestBody Paper paper, HttpServletRequest request, HttpServletResponse response){
        PaperResponseDto result = new PaperResponseDto();
        String loginId = SessionUtils.GetLoginId(request);
        if (loginId.isEmpty()){
            result.statusCode = "fail";
            result.message = "not found user";
        }

        return paperService.deletePaper(paper);
    }
}
