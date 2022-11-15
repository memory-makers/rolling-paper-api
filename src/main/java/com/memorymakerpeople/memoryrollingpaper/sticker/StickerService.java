package com.memorymakerpeople.memoryrollingpaper.sticker;

import com.memorymakerpeople.memoryrollingpaper.card.model.PostCardResponse;
import com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus;
import com.memorymakerpeople.memoryrollingpaper.exception.CustomException;
import com.memorymakerpeople.memoryrollingpaper.paper.PaperRepository;
import com.memorymakerpeople.memoryrollingpaper.paper.model.Paper;
import com.memorymakerpeople.memoryrollingpaper.sticker.model.*;
import com.memorymakerpeople.memoryrollingpaper.utils.ValidUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class StickerService {

    private final StickerRepository stickerRepository;
    private final PaperRepository paperRepository;

    public PostStickerRes createSticker(List<PostStickerReq> postStickerReq, Long paperId) {
        if(postStickerReq.isEmpty()) {
            stickerRepository.deleteByPaperId(paperId);
            return null;
        }
        Optional<Paper> optionalPaper = paperRepository.findByPaperId(paperId);

        if(optionalPaper.isEmpty()) {
            throw new CustomException(FOUND_PAPER_INFO_NULL);
        }
        Paper paper = optionalPaper.get();
        if (ValidUtil.validCardDueDate(paper.getDueDate())) {
            throw new CustomException(INVALID_CARD_DUE_DATE);
        }

        List<Sticker> result = stickerClassification(postStickerReq);

        if(result.isEmpty()) {
            return null;
        }

        List<StickerRes> stickerResList = convertStickerToResDto(result);
        return new PostStickerRes(stickerResList);
    }

    public GetStickerListRes selectStickerList(Long paperId) {
        List<Sticker> result = stickerRepository.findByPaperId(paperId);

        if(result.isEmpty()) {
            return null;
        }

        List<StickerRes> stickerResList = convertStickerToResDto(result);
        return new GetStickerListRes(stickerResList);
    }

    private List<StickerRes> convertStickerToResDto(List<Sticker> stickers) {
        List<StickerRes> stickerResList = new ArrayList<>();
        for (Sticker sticker : stickers) {
            StickerRes stickerRes = sticker.GetStickerRes();
            stickerResList.add(stickerRes);
        }

        return stickerResList;
    }

    private List<Sticker> stickerClassification(List<PostStickerReq> postStickerReq) {
        List<Sticker> stickers = new ArrayList<>();
        List<Sticker> deleteStickers = new ArrayList<>();

        for (PostStickerReq stickerReq : postStickerReq) {
            Sticker sticker = stickerReq.toEntity();

            if(stickerReq.getRequestType().equals("create") || stickerReq.getRequestType().equals("update")) {
                stickers.add(sticker);
            } else if(stickerReq.getRequestType().equals("delete")) {
                deleteStickers.add(sticker);
            }
        }

        log.debug("stickers = {}", stickers);
        log.debug("deleteStickers = {}", deleteStickers);
        List<Sticker> result = stickerRepository.saveAll(stickers);
        stickerRepository.deleteAll(deleteStickers);

        return result;
    }
}
