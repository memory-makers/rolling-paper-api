package com.memorymakerpeople.memoryrollingpaper.card.model;

import com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostCardResponse {
    private Card card;
    private BaseResponseStatus status;

}
