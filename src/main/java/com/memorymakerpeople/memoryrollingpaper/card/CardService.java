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

@Service
@Slf4j
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    private final PaperRepository paperRepository;

    //리펙토링 필요
    public GetCardResponse getCardList(Long paperId){
        List<Card> cardList = cardRepository.findByPaperId(paperId);

        if(cardList.isEmpty()) {
            return null;
        }
        List<CardRes> resultList = converCardToCardRes(cardList);
        return new GetCardResponse(resultList);
    }

    private List<CardRes> converCardToCardRes(List<Card> cardList) {
        List<CardRes> resultList = new ArrayList<>();

        for (Card card : cardList) {
            CardRes cardRes = card.GetCardRes();
            resultList.add(cardRes);
        }
        return resultList;
    }

    //리펙토링 필요
    public PostCardResponse createCard(PostCardReq card) {

        Optional<Paper> Paper = paperRepository.findByPaperId(card.getPaperId());
        BaseResponseStatus INVALID_CARD_DUE_DATE = ValidUtil.validCardDueDate(Paper);

        if (INVALID_CARD_DUE_DATE != null) {
            throw new CustomException(INVALID_CARD_DUE_DATE);
        }

        Card save = cardRepository.save(card.toEntity());
        Long result = save.getCardId();

        return new PostCardResponse(result);
    }
}
