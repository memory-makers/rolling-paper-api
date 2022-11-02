package com.memorymakerpeople.memoryrollingpaper.paper.model;

import com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter
@ToString
public class PostPaperReq {
    @NotBlank
    private String paperTitle;
    @NotBlank
    private String theme;
    private String paperUrl;
    private String deleteYn;
    private String userId;
    @NotNull
    private Timestamp dueDate;

    public Paper toEntity() {
        return Paper.builder().
                paperTitle(this.paperTitle).
                theme(this.theme).
                paperUrl(this.paperUrl).
                deleteYn(this.deleteYn).
                userId(this.userId).
                dueDate(this.dueDate).
                build();
    }

    public void changeIdAndUrl(String userId, String paperUrl) {
        this.userId = userId;
        this.paperUrl = paperUrl;
    }
}
