package com.memorymakerpeople.memoryrollingpaper.card;

import com.memorymakerpeople.memoryrollingpaper.card.model.Card;
import com.memorymakerpeople.memoryrollingpaper.card.model.GetCardResponse;
import com.memorymakerpeople.memoryrollingpaper.card.model.PostCardResponse;
import com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public GetCardResponse getCardList(String paperId){
        List<Card> cardList = cardRepository.findByPaperId(paperId);
        if(ObjectUtils.isEmpty(cardList)) {
            return new GetCardResponse(null, BaseResponseStatus.EMPTY_CARD_LIST);
        }

        return new GetCardResponse(cardList, BaseResponseStatus.SUCCESS);
    }

    public PostCardResponse createCard(Card card) {
        return new PostCardResponse(cardRepository.save(card), BaseResponseStatus.SUCCESS);
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
