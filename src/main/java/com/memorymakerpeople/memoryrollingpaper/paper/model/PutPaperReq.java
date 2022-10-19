package com.memorymakerpeople.memoryrollingpaper.paper.model;

import com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus;
import lombok.*;

import java.sql.Timestamp;

@Getter
@ToString
public class PutPaperReq {
    private Long paperId;
    private String paperTitle;
    private String theme;
    private String paperUrl;
    private String deleteYn;
    private String userId;
    private Timestamp dueDate;

    public Paper toEntity() {
        return Paper.builder().
                paperId(this.paperId).
                paperTitle(this.paperTitle).
                theme(this.theme).
                paperUrl(this.paperUrl).
                deleteYn(this.deleteYn).
                userId(this.userId).
                dueDate(this.dueDate).
                build();
    }
}
