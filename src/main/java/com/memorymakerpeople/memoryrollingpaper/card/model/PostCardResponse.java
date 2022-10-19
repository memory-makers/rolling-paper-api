package com.memorymakerpeople.memoryrollingpaper.card.model;

import com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostCardResponse {
    private Long cardId;
    //private Card card; //리펙토링
    private BaseResponseStatus status;

}
