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

    private final StickerService stickerService;

    @PostMapping
    @ApiOperation(value = "스티커 편집기능", notes = "스티커를 편집합니다. 오픈 날짜 이후 카드 편집 불가")
    public BaseResponse<PostStickerRes> createSticker(@RequestBody List<PostStickerReq> postStickerReq){
        log.info("postStickerReq = {}", postStickerReq);
        return new BaseResponse<>(stickerService.createSticker(postStickerReq));
    }

    @GetMapping
    @ApiOperation(value = "스티커 리스트 보기", notes = "paperId를 기준으로 스티커를 조회합니다.")
    public BaseResponse<GetStickerListRes> stickerList(Long paperId) {
        return new BaseResponse<>(stickerService.selectStickerList(paperId));
    }
}
