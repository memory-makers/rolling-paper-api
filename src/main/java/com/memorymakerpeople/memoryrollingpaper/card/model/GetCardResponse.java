package com.memorymakerpeople.memoryrollingpaper.card.model;

import com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetCardResponse {
    private List<Card> card;
    private BaseResponseStatus status;

}
