package com.memorymakerpeople.memoryrollingpaper.dto;

import com.memorymakerpeople.memoryrollingpaper.domain.Card;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class PaperResponseDto extends DefaultResponseDto{
    private int paperId;
    private String paperTitle;
    private Timestamp dueDt;
    private String theme;
    private String paperUrl;
    private String deleteYn;
    private String openStatus;
    private String userId;
    private List<Card> cardList;
}
