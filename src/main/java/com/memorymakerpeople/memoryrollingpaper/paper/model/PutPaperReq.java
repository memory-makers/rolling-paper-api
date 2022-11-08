package com.memorymakerpeople.memoryrollingpaper.paper.model;

import com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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

    public Paper toEntity(Paper paper) {
        return Paper.builder().
                paperId(this.paperId).
                paperTitle(this.paperTitle).
                theme(this.theme).
                dueDate(this.dueDate).
                userId(paper.getUserId()).
                paperUrl(paper.getPaperUrl()).
                deleteYn(paper.getDeleteYn()).
                createdAt(paper.getCreatedAt()).
                updatedAt(paper.getUpdatedAt()).
                build();
    }
}
