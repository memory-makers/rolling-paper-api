package com.memorymakerpeople.memoryrollingpaper.card.model;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class CardRes {

    private Long cardId;
    private String cardText;
    private String cardColor;
    private Long paperId;
    private String fontStyle;
    private String fontColor;
    private String cardWriter;

    @Builder
    public CardRes(Long cardId, String cardText, String cardColor, Long paperId, String fontStyle, String fontColor,String cardWriter) {
        this.cardId = cardId;
        this.cardText = cardText;
        this.cardColor = cardColor;
        this.paperId = paperId;
        this.fontStyle = fontStyle;
        this.fontColor = fontColor;
        this.cardWriter = cardWriter;
    }
}
