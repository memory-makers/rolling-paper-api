package com.memorymakerpeople.memoryrollingpaper.dto;

import lombok.Data;

@Data
public class CardRequestDto{
    private int cardId;
    private String paperId;
    private String cardText;
    private String cardColor;
    private String fontStyle;
    private String fontColor;

}
