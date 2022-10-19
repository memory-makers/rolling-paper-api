package com.memorymakerpeople.memoryrollingpaper.card.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostCardReq {
    private String cardText;
    private String cardColor;
    private Long paperId;
    private String fontStyle;
    private String fontColor;
    private String cardWriter;


    public Card toEntity() {
        return Card.builder().
                cardText(this.cardText).
                cardColor(this.cardColor).
                paperId(this.paperId).
                fontStyle(this.fontStyle).
                fontColor(this.fontColor).
                cardWriter(this.cardWriter)
                .build();
    }

}
