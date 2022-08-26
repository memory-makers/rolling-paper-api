package com.memorymakerpeople.memoryrollingpaper.card;

import com.memorymakerpeople.memoryrollingpaper.card.model.Card;
import com.memorymakerpeople.memoryrollingpaper.card.model.postCardResponse;
import com.memorymakerpeople.memoryrollingpaper.config.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/card")
@Api(tags = {"카드 관리 API"})
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping
    @ApiOperation(value = "카드 생성", notes = "하나의 롤링페이퍼에 있는 카드를 생성 합니다.")
    public BaseResponse<postCardResponse> createCard(@RequestBody Card card){
        return new BaseResponse<>(cardService.createCard(card));
    }

    /*@PutMapping
    @ApiOperation(value = "카드 수정", notes = "하나의 롤링페이퍼에 있는 카드를 수정 합니다. = 미구현")
    public CardResponseDto updateCard(Card card) {
        return cardService.updateCard(card);
    }

    @PutMapping
    @ApiOperation(value = "카드 삭제", notes = "하나의 롤링페이퍼에 있는 카드를 삭제 합니다. = 미구현")
    public CardResponseDto deleteCard(Card card, HttpServletRequest request, HttpServletResponse response) {
        return cardService.deleteCard(card);
    }*/

}
