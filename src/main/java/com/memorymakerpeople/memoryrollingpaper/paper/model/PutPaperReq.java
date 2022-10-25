package com.memorymakerpeople.memoryrollingpaper.paper.model;

import com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;

@Getter
@ToString
public class PutPaperReq {
    @NotBlank
    @Positive
    private Long paperId;
    @NotBlank
    private String paperTitle;
    @NotBlank
    private String theme;
    @NotEmpty
    private Timestamp dueDate;

    public Paper toEntity() {
        return Paper.builder().
                paperId(this.paperId).
                paperTitle(this.paperTitle).
                theme(this.theme).
                dueDate(this.dueDate).
                build();
    }
}
