package com.memorymakerpeople.memoryrollingpaper.card;

import com.memorymakerpeople.memoryrollingpaper.card.model.Card;
import com.memorymakerpeople.memoryrollingpaper.card.model.GetCardResponse;
import com.memorymakerpeople.memoryrollingpaper.card.model.PostCardReq;
import com.memorymakerpeople.memoryrollingpaper.card.model.PostCardResponse;
import com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus;
import com.memorymakerpeople.memoryrollingpaper.paper.PaperRepository;
import com.memorymakerpeople.memoryrollingpaper.paper.model.Paper;
import com.memorymakerpeople.memoryrollingpaper.utils.ValidUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardService {

    @Autowired
    private final CardRepository cardRepository;

    private final PaperRepository paperRepository;

    //리펙토링 필요
    public GetCardResponse getCardList(Long paperId){
        List<Card> cardList = cardRepository.findByPaperId(paperId);
        if(ObjectUtils.isEmpty(cardList)) {
            return new GetCardResponse(null, BaseResponseStatus.EMPTY_CARD_LIST);
        }

        return new GetCardResponse(cardList, BaseResponseStatus.SUCCESS);
    }

    //리펙토링 필요
    public PostCardResponse createCard(PostCardReq card) {
        Optional<Paper> Paper = paperRepository.findByPaperId(card.getPaperId());
        BaseResponseStatus INVALID_CARD_DUE_DATE = ValidUtil.validCardDueDate(Paper);
        if (INVALID_CARD_DUE_DATE != null) {
            return new PostCardResponse(null,INVALID_CARD_DUE_DATE);
        }

        Card result = cardRepository.save(card.toEntity());
        log.info("saved card = {}", result);
        return new PostCardResponse(result, BaseResponseStatus.SUCCESS);
    }
}
