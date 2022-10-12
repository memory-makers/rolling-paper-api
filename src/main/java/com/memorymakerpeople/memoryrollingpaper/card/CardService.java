package com.memorymakerpeople.memoryrollingpaper.card;

import com.memorymakerpeople.memoryrollingpaper.card.model.Card;
import com.memorymakerpeople.memoryrollingpaper.card.model.GetCardResponse;
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

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    private final PaperRepository paperRepository;

    //리펙토링 필요
    public GetCardResponse getCardList(String paperId){
        List<Card> cardList = cardRepository.findByPaperId(paperId);
        if(ObjectUtils.isEmpty(cardList)) {
            return new GetCardResponse(null, BaseResponseStatus.EMPTY_CARD_LIST);
        }

        return new GetCardResponse(cardList, BaseResponseStatus.SUCCESS);
    }

    //리펙토링 필요
    public PostCardResponse createCard(Card card) {
        Optional<Paper> Paper = paperRepository.findByPaperId(Long.valueOf(card.getPaperId()));
        BaseResponseStatus INVALID_CARD_DUE_DATE = ValidUtil.validCardDueDate(Paper);
        if (INVALID_CARD_DUE_DATE != null) return new PostCardResponse(null,INVALID_CARD_DUE_DATE);

        Card result = cardRepository.save(card);
        return new PostCardResponse(result, BaseResponseStatus.SUCCESS);
    }


    /*
    public CardResponseDto updateCard(Card card) {
        CardResponseDto responseDto = new CardResponseDto();
        responseDto.message = "Card Update";
        if (cardRepository.findById(card.getCardId()).isEmpty()){
            responseDto.statusCode = "fail";
        }else{
            responseDto.statusCode = "complete";
        }
        return responseDto;
    }

    public CardResponseDto deleteCard(Card card) {
        CardResponseDto responseDto = new CardResponseDto();
        responseDto.message = "Card Delete";
        if (cardRepository.findById(card.getCardId()).isEmpty()){
            responseDto.statusCode = "fail";
        }else{
            responseDto.statusCode = "complete";
        }
        return responseDto;
    }*/
}
