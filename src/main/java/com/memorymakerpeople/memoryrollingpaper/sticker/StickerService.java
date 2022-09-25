package com.memorymakerpeople.memoryrollingpaper.sticker;

import com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus;
import com.memorymakerpeople.memoryrollingpaper.sticker.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StickerService {

    @Autowired
    private StickerRepository stickerRepository;

    public PostStickerRes createSticker(List<PostStickerReq> postStickerReq) {
        log.info("postStickerReq = {}", postStickerReq);
        List<Sticker> stickers = new ArrayList<Sticker>();
        List<Sticker> deleteStickers = new ArrayList<Sticker>();
        for (PostStickerReq stickerReq : postStickerReq) {
            if(stickerReq.getRequestType().equals("create")) {
                Sticker createSticker = Sticker.builder()
                        .paperId(stickerReq.getPaperId())
                        .rotate(stickerReq.getRotate())
                        .scale(stickerReq.getScale())
                        .stickerSize(stickerReq.getStickerSize())
                        .type(stickerReq.getType())
                        .x(stickerReq.getX())
                        .y(stickerReq.getY())
                        .build();
                stickers.add(createSticker);
            }else {
                Sticker sticker = Sticker.builder()
                        .paperId(stickerReq.getPaperId())
                        .rotate(stickerReq.getRotate())
                        .scale(stickerReq.getScale())
                        .stickerId(stickerReq.getStickerId())
                        .stickerSize(stickerReq.getStickerSize())
                        .type(stickerReq.getType())
                        .x(stickerReq.getX())
                        .y(stickerReq.getY())
                        .build();
                if(stickerReq.getRequestType().equals("delete")) {
                    deleteStickers.add(sticker);
                }
                if(stickerReq.getRequestType().equals("update")) {
                    stickers.add(sticker);
                }
            }
        }

        log.info("stickers = {}", stickers);
        log.info("deleteStickers = {}", deleteStickers);
        List<Sticker> result = stickerRepository.saveAll(stickers);
        stickerRepository.deleteAll(deleteStickers);

        if(!result.isEmpty()) {
            return new PostStickerRes(result, BaseResponseStatus.SUCCESS);
        }
        return new PostStickerRes(null, BaseResponseStatus.FAILED_TO_LOAD_STICKERS);
    }

    public GetStickerListRes selectStickerList(int paperId) {
        System.out.println("paperId = " + paperId);
        List<Sticker> result = stickerRepository.findByPaperId(paperId);
        System.out.println("result = " + result);
        if(!result.isEmpty()) {
            return new GetStickerListRes(result, BaseResponseStatus.SUCCESS);
        }
        return new GetStickerListRes(null, BaseResponseStatus.FAILED_TO_LOAD_STICKERS);
    }
/*
    public GetStickerRes selectSticker(int stickerId) {
        Optional<Sticker> sticker = stickerRepository.findByStickerId(stickerId);
        if(sticker.isPresent()) {
            return new GetStickerRes(sticker.get(), BaseResponseStatus.SUCCESS);
        }
        return new GetStickerRes(null, BaseResponseStatus.FAILED_TO_LOAD_STICKER);
    }

    public PutStickerRes updateSticker(PutStickerReq putStickerReq) {
        return new PutStickerRes(stickerRepository.save(putStickerReq.getSticker()),BaseResponseStatus.SUCCESS);
    }

    public DeleteStickerRes deleteSticker(int stickerId) {
        stickerRepository.deleteById(stickerId);
        return new DeleteStickerRes(BaseResponseStatus.SUCCESS);
    }*/
}
