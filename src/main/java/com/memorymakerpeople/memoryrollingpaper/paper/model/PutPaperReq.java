package com.memorymakerpeople.memoryrollingpaper.paper.model;

import com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

@Getter
@ToString
public class PutPaperReq {
    @NotBlank
    private Long paperId;
    @NotEmpty
    private String paperTitle;
    @NotBlank
    private String theme;
    @NotBlank
    private String deleteYn;
    @NotBlank
    private String userId;
    @NotEmpty
    private Timestamp dueDate;

    public Paper toEntity() {
        return Paper.builder().
                paperId(this.paperId).
                paperTitle(this.paperTitle).
                theme(this.theme).
                deleteYn(this.deleteYn).
                userId(this.userId).
                dueDate(this.dueDate).
                build();
    }
}
