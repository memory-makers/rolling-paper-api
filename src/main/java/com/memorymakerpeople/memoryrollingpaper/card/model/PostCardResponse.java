package com.memorymakerpeople.memoryrollingpaper.card.model;

import com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostCardResponse {
    /*private Long cardId;
    private String cardText;
    private String cardColor;
    private Long paperId;
    private String fontStyle;
    private String fontColor;
    private String cardWriter;*/
    private Card card; //리펙토링
    private BaseResponseStatus status;

}
