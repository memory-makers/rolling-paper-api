package com.memorymakerpeople.memoryrollingpaper.sticker;

import com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus;
import com.memorymakerpeople.memoryrollingpaper.sticker.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StickerService {

    @Autowired
    private StickerRepository stickerRepository;

    public PostStickerRes createSticker(PostStickerReq postStickerReq) {
        Sticker sticker = Sticker.builder()
                .stickerSize(postStickerReq.getStickerSize())
                .stickerId(postStickerReq.getStickerId())
                .paperId(postStickerReq.getPaperId())
                .rotate(postStickerReq.getRotate())
                .scale(postStickerReq.getScale())
                .type(postStickerReq.getType())
                .x(postStickerReq.getX())
                .y(postStickerReq.getY())
                .build();
        //stickerRepository.save(postStickerReq.getSticker())
        return new PostStickerRes(stickerRepository.save(sticker), BaseResponseStatus.SUCCESS);
    }

    public GetStickerListRes selectStickerList(int paperId) {
        List<Sticker> result = stickerRepository.findByPaperId(paperId);
        if(!result.isEmpty()) {
            return new GetStickerListRes(result, BaseResponseStatus.SUCCESS);
        }
        return new GetStickerListRes(null, BaseResponseStatus.FAILED_TO_LOAD_STICKERS);
    }

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
    }
}
