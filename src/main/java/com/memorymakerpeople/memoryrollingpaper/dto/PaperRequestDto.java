package com.memorymakerpeople.memoryrollingpaper.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PaperRequestDto{
    private int paperId;
    private String paperTitle;
    private Timestamp dueDt;
    private String theme;
    private String paperUrl;
    private String deleteYn;
    private String openStatus;
    private String userId;
}
