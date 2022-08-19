package com.memorymakerpeople.memoryrollingpaper.card.model;

import lombok.Data;

@Data
public class CardResponseDto{
    private int cardId;
    private String cardText;
    private String cardColor;
    private String paperId;
    private String fontStyle;
    private String fontColor;
}
