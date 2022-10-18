package com.memorymakerpeople.memoryrollingpaper.paper.model;

import com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
@Entity
@DynamicUpdate // 변경한 필드만 대응
@DynamicInsert // null로 들어가야 하는 경우 대응(기본값)
@Table(name = "tbl_paper")
public class Paper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paperId;
    private String paperTitle;
    private String theme;
    private String paperUrl;
    private String deleteYn;
    private String userId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp dueDate;

    @Builder
    public Paper(Long paperId, String paperTitle, String theme, String paperUrl, String deleteYn, String userId, Timestamp dueDate){
        this.paperId = paperId;
        this.paperTitle = paperTitle;
        this.theme = theme;
        this.paperUrl = paperUrl;
        this.deleteYn = deleteYn;
        this.userId = userId;
        this.dueDate = dueDate;
    }

    public GetPaperRes toGetPaperRes() {
        return new GetPaperRes.GetPaperResBuilder().
                paperId(this.paperId).
                paperTitle(this.paperTitle).
                theme(this.theme).
                paperUrl(this.paperUrl).
                deleteYn(this.deleteYn).
                userId(this.userId).
                createdAt(this.createdAt).
                updatedAt(this.updatedAt).
                dueDate(this.dueDate).
                build();
    }

}