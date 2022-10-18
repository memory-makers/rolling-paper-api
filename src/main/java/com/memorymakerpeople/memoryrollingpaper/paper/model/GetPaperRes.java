package com.memorymakerpeople.memoryrollingpaper.paper.model;

import com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus;
import lombok.*;

import java.sql.Time;
import java.sql.Timestamp;

@Getter
@ToString
@NoArgsConstructor
public class GetPaperRes {
    private Long paperId;
    private String paperTitle;
    private String theme;
    private String paperUrl;
    private String deleteYn;
    private String userId;
    private Timestamp dueDate;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private BaseResponseStatus status;

    @Builder
    public GetPaperRes(Long paperId, String paperTitle, String theme, String paperUrl, String deleteYn, String userId,Timestamp createdAt,Timestamp updatedAt, Timestamp dueDate){
        this.paperId = paperId;
        this.paperTitle = paperTitle;
        this.theme = theme;
        this.paperUrl = paperUrl;
        this.deleteYn = deleteYn;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userId = userId;
        this.dueDate = dueDate;
    }

    public void updateStatus(BaseResponseStatus status) {
        this.status = status;
    }
}
