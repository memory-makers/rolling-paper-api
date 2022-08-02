package com.memorymakerpeople.memoryrollingpaper.service;

import com.memorymakerpeople.memoryrollingpaper.domain.Card;
import com.memorymakerpeople.memoryrollingpaper.dto.CardResponseDto;
import com.memorymakerpeople.memoryrollingpaper.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public List<Card> getCardList(String paperId){
        return cardRepository.findByPaperId(paperId);
    }

    public CardResponseDto createCard(Card card) {
        CardResponseDto responseDto = new CardResponseDto();
        responseDto.message = "Card Create";
        if (cardRepository.save(card).equals(null)){
            responseDto.statusCode = "fail";
        }else{
            responseDto.statusCode = "complete";
        }
        return responseDto;
    }

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
    }
}
