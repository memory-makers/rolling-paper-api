package com.memorymakerpeople.memoryrollingpaper.controller;

import com.memorymakerpeople.memoryrollingpaper.domain.Sticker;
import com.memorymakerpeople.memoryrollingpaper.dto.StickerRequestDto;
import com.memorymakerpeople.memoryrollingpaper.dto.StickerResponseDto;
import com.memorymakerpeople.memoryrollingpaper.service.StickerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sticker")
@Api(tags = {"스티커 관리 API"})
public class StickerController {

    @Autowired
    private final StickerService stickerService;

    @PostMapping
    @ApiOperation(value = "스티커 생성", notes = "스티커를 삭제합니다.")
    public StickerResponseDto createSticker(StickerRequestDto stickerRequestDto, HttpServletRequest request){
        return stickerService.createSticker(stickerRequestDto);
    }

    @GetMapping("/paperList")
    @ApiOperation(value = "스티커 리스트 보기", notes = "paperId를 기준으로 스티커를 조회합니다.")
    public List<Sticker> stickerList(StickerRequestDto stickerRequestDto, HttpServletRequest request) {
        return stickerService.selectStickerList(stickerRequestDto);
    }

    @GetMapping
    @ApiOperation(value = "하나의 스티커 보기", notes = "stickerId를 기준으로 스티커를 조회합니다.")
    public StickerResponseDto stickerDetail(StickerRequestDto stickerRequestDto, HttpServletRequest request) {
        return stickerService.selectSticker(stickerRequestDto);
    }

    @PutMapping
    @ApiOperation(value = "스티커 업데이트", notes = "stickerId를 기준으로 스티커를 업데이트합니다.")
    public StickerResponseDto updateSticker(StickerRequestDto stickerRequestDto, HttpServletRequest request){
        return stickerService.updateSticker(stickerRequestDto);
    }

    @DeleteMapping
    @ApiOperation(value = "스티커 삭제", notes = "stickerId를 기준으로 스티커를 삭제합니다.")
    public StickerResponseDto deleteSticker(StickerRequestDto stickerRequestDto, HttpServletRequest request){
        return stickerService.deleteSticker(stickerRequestDto);
    }
}
