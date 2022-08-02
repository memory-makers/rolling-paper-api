package com.memorymakerpeople.memoryrollingpaper.dto;

import lombok.Data;

@Data
public class CardResponseDto extends DefaultResponseDto{
    private int cardId;
    private String cardText;
    private String cardColor;
    private String paperId;
    private String fontStyle;
    private String fontColor;
}
