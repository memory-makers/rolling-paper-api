package com.memorymakerpeople.memoryrollingpaper.card;

import com.memorymakerpeople.memoryrollingpaper.card.model.Card;
import com.memorymakerpeople.memoryrollingpaper.card.model.GetCardResponse;
import com.memorymakerpeople.memoryrollingpaper.card.model.PostCardReq;
import com.memorymakerpeople.memoryrollingpaper.card.model.PostCardResponse;
import com.memorymakerpeople.memoryrollingpaper.config.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/cards")
@Api(tags = {"카드 관리 API"})
@Slf4j
public class CardController {

    private final CardService cardService;

    @PostMapping
    @ApiOperation(value = "카드 생성", notes = "하나의 롤링페이퍼에 있는 카드를 생성 합니다. 오픈날짜 이후 카드 생성 불가")
    public BaseResponse<PostCardResponse> createCard(@RequestBody PostCardReq card){
        log.info("card = {}",card);
        return new BaseResponse<>(cardService.createCard(card));
    }

    @ApiOperation(value = "카드 조회", notes = "PaperId를 기준으로 카드를 조회합니다.")
    @GetMapping("/{paperId}")
    public BaseResponse<GetCardResponse> getCard(@PathVariable Long paperId) {
        log.info("paperId = {}",paperId);
        return new BaseResponse<>(cardService.getCardList(paperId));
    }
}
