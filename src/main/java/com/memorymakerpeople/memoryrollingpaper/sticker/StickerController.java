package com.memorymakerpeople.memoryrollingpaper.sticker;

import com.memorymakerpeople.memoryrollingpaper.config.BaseResponse;
import com.memorymakerpeople.memoryrollingpaper.sticker.model.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/stickers")
@Api(tags = {"스티커 관리 API"})
@Slf4j
public class StickerController {

    @Autowired
    private final StickerService stickerService;

    @PostMapping
    @ApiOperation(value = "스티커 편집기능", notes = "스티커를 편집합니다. 오픈 날짜 이후 카드 편집 불가")
    public BaseResponse<PostStickerRes> createSticker(@RequestBody List<PostStickerReq> postStickerReq){
        log.info("postStickerReq = {}", postStickerReq);
        return new BaseResponse<>(stickerService.createSticker(postStickerReq));
    }

    @GetMapping
    @ApiOperation(value = "스티커 리스트 보기", notes = "paperId를 기준으로 스티커를 조회합니다.")
    public BaseResponse<GetStickerListRes> stickerList(int paperId) {
        return new BaseResponse<>(stickerService.selectStickerList(paperId));
    }
/*
    @GetMapping("/{stickerId}")
    @ApiOperation(value = "하나의 스티커 보기", notes = "stickerId를 기준으로 스티커를 조회합니다.")
    public BaseResponse<GetStickerRes> stickerDetail(@PathVariable int stickerId) {
        return new BaseResponse<>(stickerService.selectSticker(stickerId));
    }

    @PutMapping("/{stickerId}")
    @ApiOperation(value = "스티커 업데이트", notes = "stickerId를 기준으로 스티커를 업데이트합니다.")
    public BaseResponse<PutStickerRes> updateSticker(@RequestBody PutStickerReq putStickerReq){
        return new BaseResponse<>(stickerService.updateSticker(putStickerReq));
    }

    @DeleteMapping("/{stickerId}")
    @ApiOperation(value = "스티커 삭제", notes = "stickerId를 기준으로 스티커를 삭제합니다.")
    public BaseResponse<DeleteStickerRes> deleteSticker(@PathVariable int stickerId) {
        return new BaseResponse<>(stickerService.deleteSticker(stickerId));
    }*/
}
