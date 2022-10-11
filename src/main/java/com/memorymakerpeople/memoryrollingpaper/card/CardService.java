package com.memorymakerpeople.memoryrollingpaper.card;

import com.memorymakerpeople.memoryrollingpaper.card.model.Card;
import com.memorymakerpeople.memoryrollingpaper.card.model.GetCardResponse;
import com.memorymakerpeople.memoryrollingpaper.card.model.PostCardResponse;
import com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@Slf4j
public class CardService {

    @Autowired
    private CardRepository cardRepository;

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
        Card result = cardRepository.save(card);
        log.info("saved card = {}", result);
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
