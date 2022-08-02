package com.memorymakerpeople.memoryrollingpaper.service;

import com.memorymakerpeople.memoryrollingpaper.domain.Card;
import com.memorymakerpeople.memoryrollingpaper.domain.Sticker;
import com.memorymakerpeople.memoryrollingpaper.dto.CardResponseDto;
import com.memorymakerpeople.memoryrollingpaper.dto.StickerRequestDto;
import com.memorymakerpeople.memoryrollingpaper.dto.StickerResponseDto;
import com.memorymakerpeople.memoryrollingpaper.repository.CardRepository;
import com.memorymakerpeople.memoryrollingpaper.repository.StickerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StickerService {

    @Autowired
    private StickerRepository stickerRepository;

    public StickerResponseDto selectSticker(StickerRequestDto stickerRequestDto) {
        Optional<Sticker> byStickerId = stickerRepository.findByStickerId(stickerRequestDto.getSticker().getStickerId());
        StickerResponseDto result = new StickerResponseDto();
        if (byStickerId.isPresent()){
            result.setSticker(byStickerId.get());
        }
        return result;
    }

    public StickerResponseDto createSticker(StickerRequestDto stickerRequestDto) {
        StickerResponseDto result = new StickerResponseDto();
        result.setSticker(stickerRepository.save(stickerRequestDto.getSticker()));
        if(result.getSticker() != null){
            result.statusCode = "complete";
        }else{
            result.statusCode = "fail";
        }
        result.message = "Sticker Create";
        return result;
    }

    public List<Sticker> selectStickerList(StickerRequestDto stickerRequestDto) {
        return stickerRepository.findByPaperId(stickerRequestDto.getSticker().getPaperId());

    }

    public StickerResponseDto updateSticker(StickerRequestDto stickerRequestDto) {
        StickerResponseDto result = new StickerResponseDto();
        result.setSticker(stickerRepository.save(stickerRequestDto.getSticker()));
        if(result.getSticker().getStickerId() > 0){
            result.statusCode = "complete";
        }else{
            result.statusCode = "fail";
        }
        result.message = "Sticker Update";
        return result;
    }

    public StickerResponseDto deleteSticker(StickerRequestDto stickerRequestDto) {
        StickerResponseDto result = new StickerResponseDto();
        stickerRepository.delete(stickerRequestDto.getSticker());
        result.statusCode = "complete";
        result.message = "Sticker delete";
        return result;
    }
/*
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
    }*/
}
