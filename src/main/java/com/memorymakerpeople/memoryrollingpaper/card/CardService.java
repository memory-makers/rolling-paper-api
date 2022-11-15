package com.memorymakerpeople.memoryrollingpaper.card;

import com.memorymakerpeople.memoryrollingpaper.card.model.*;
import com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus;
import com.memorymakerpeople.memoryrollingpaper.exception.CustomException;
import com.memorymakerpeople.memoryrollingpaper.paper.PaperRepository;
import com.memorymakerpeople.memoryrollingpaper.paper.model.Paper;
import com.memorymakerpeople.memoryrollingpaper.utils.ValidUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus.INVALID_CARD_DUE_DATE;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    private final PaperRepository paperRepository;

    public GetCardResponse getCardList(Long paperId){
        List<Card> cardList = cardRepository.findByPaperId(paperId);

        if(cardList.isEmpty()) {
            return null;
        }
        List<CardRes> resultList = convertCardToCardRes(cardList);
        return new GetCardResponse(resultList);
    }

    public PostCardResponse createCard(PostCardReq card) {
        Optional<Paper> optionalPaper = paperRepository.findByPaperId(card.getPaperId());

        if(optionalPaper.isEmpty()) {
            throw new CustomException(BaseResponseStatus.FOUND_PAPER_INFO_NULL);
        }
        Paper paper = optionalPaper.get();
        log.info("paper = {}",paper);
        if (ValidUtil.validCardDueDate(paper.getDueDate())) {
            throw new CustomException(INVALID_CARD_DUE_DATE);
        }

        Card save = cardRepository.save(card.toEntity());
        Long result = save.getCardId();

        return new PostCardResponse(result);
    }

    private List<CardRes> convertCardToCardRes(List<Card> cardList) {
        List<CardRes> resultList = new ArrayList<>();

        for (Card card : cardList) {
            CardRes cardRes = card.GetCardRes();
            resultList.add(cardRes);
        }
        return resultList;
    }
}
