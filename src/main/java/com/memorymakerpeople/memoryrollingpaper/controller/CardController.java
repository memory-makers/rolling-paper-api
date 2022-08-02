package com.memorymakerpeople.memoryrollingpaper.controller;

import com.memorymakerpeople.memoryrollingpaper.domain.Card;
import com.memorymakerpeople.memoryrollingpaper.domain.Paper;
import com.memorymakerpeople.memoryrollingpaper.dto.CardResponseDto;
import com.memorymakerpeople.memoryrollingpaper.service.CardService;
import com.memorymakerpeople.memoryrollingpaper.util.SessionUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@RequestMapping("/card")
@Api(tags = {"카드 관리 API"})
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping
    @ApiOperation(value = "카드 생성", notes = "하나의 롤링페이퍼에 있는 카드를 생성 합니다.")
    public CardResponseDto createCard(@RequestBody Card card, HttpServletRequest request, HttpServletResponse response){
        return cardService.createCard(card);
    }

/*    @PutMapping
    @ApiOperation(value = "카드 수정", notes = "하나의 롤링페이퍼에 있는 카드를 수정 합니다. = 미구현")
    public CardResponseDto updateCard(Card card, HttpServletRequest request, HttpServletResponse response) {
        String loginId = SessionUtils.GetLoginId(request);
        if (loginId.isEmpty()) {
            return null;
        }
        return cardService.updateCard(card);
    }

    @PutMapping
    @ApiOperation(value = "카드 삭제", notes = "하나의 롤링페이퍼에 있는 카드를 삭제 합니다. = 미구현")
    public CardResponseDto deleteCard(Card card, HttpServletRequest request, HttpServletResponse response) {
        String loginId = SessionUtils.GetLoginId(request);
        if (loginId.isEmpty()) {
            return null;
        }
        return cardService.deleteCard(card);
    }*/

}
