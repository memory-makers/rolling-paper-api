package com.memorymakerpeople.memoryrollingpaper.paper.model;

import com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus;
import lombok.*;

import javax.validation.constraints.*;
import java.sql.Timestamp;

@Getter
@ToString
public class PutPaperReq {
    @NotNull
    @Positive
    private Long paperId;
    @NotBlank
    private String paperTitle;
    @NotBlank
    private String theme;
    @NotNull
    private Timestamp dueDate;
    private String userId;

    public Paper toEntity(String userId) {
        return Paper.builder().
                paperId(this.paperId).
                paperTitle(this.paperTitle).
                theme(this.theme).
                dueDate(this.dueDate).
                userId(userId).
                build();
    }
}
