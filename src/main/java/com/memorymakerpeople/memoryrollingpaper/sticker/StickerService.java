package com.memorymakerpeople.memoryrollingpaper.sticker;

import com.memorymakerpeople.memoryrollingpaper.card.model.PostCardResponse;
import com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus;
import com.memorymakerpeople.memoryrollingpaper.paper.PaperRepository;
import com.memorymakerpeople.memoryrollingpaper.paper.model.Paper;
import com.memorymakerpeople.memoryrollingpaper.sticker.model.*;
import com.memorymakerpeople.memoryrollingpaper.utils.ValidUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class StickerService {

    @Autowired
    private final StickerRepository stickerRepository;

    @Autowired
    private final PaperRepository paperRepository;

    public PostStickerRes createSticker(List<PostStickerReq> postStickerReq) {

        Long paperId = postStickerReq.get(0).getPaperId();
        Optional<Paper> Paper = paperRepository.findByPaperId(paperId);

        BaseResponseStatus INVALID_CARD_DUE_DATE = ValidUtil.validCardDueDate(Paper);
        if (INVALID_CARD_DUE_DATE != null) {
            return new PostStickerRes(null,INVALID_CARD_DUE_DATE);
        }

        List<Sticker> stickers = new ArrayList<>();
        List<Sticker> deleteStickers = new ArrayList<>();
        stickerClassification(postStickerReq, stickers, deleteStickers);

        log.info("stickers = {}", stickers);
        log.info("deleteStickers = {}", deleteStickers);
        List<Sticker> result = stickerRepository.saveAll(stickers);
        stickerRepository.deleteAll(deleteStickers);

        if(!result.isEmpty()) {
            List<StickerRes> stickerResList = convertStickerToResDto(result);
            return new PostStickerRes(stickerResList, BaseResponseStatus.SUCCESS);
        }
        return new PostStickerRes(null, BaseResponseStatus.FAILED_TO_LOAD_STICKERS);
    }

    public GetStickerListRes selectStickerList(Long paperId) {
        List<Sticker> result = stickerRepository.findByPaperId(paperId);

        if(!result.isEmpty()) {
            List<StickerRes> stickerResList = convertStickerToResDto(result);
            return new GetStickerListRes(stickerResList, BaseResponseStatus.SUCCESS);
        }
        return new GetStickerListRes(null, BaseResponseStatus.FAILED_TO_LOAD_STICKERS);
    }

    private List<StickerRes> convertStickerToResDto(List<Sticker> stickers) {
        List<StickerRes> stickerResList = new ArrayList<>();
        for (Sticker sticker : stickers) {
            StickerRes stickerRes = sticker.GetStickerRes();
            stickerResList.add(stickerRes);
        }

        return stickerResList;
    }

    private void stickerClassification(List<PostStickerReq> postStickerReq, List<Sticker> stickers, List<Sticker> deleteStickers) {
        for (PostStickerReq stickerReq : postStickerReq) {
            Sticker sticker = stickerReq.toEntity();
            if(stickerReq.getRequestType().equals("create") || stickerReq.getRequestType().equals("update")) {
                stickers.add(sticker);
            } else if(stickerReq.getRequestType().equals("delete")) {
                deleteStickers.add(sticker);
            }
        }
    }
}
