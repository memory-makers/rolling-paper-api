package com.memorymakerpeople.memoryrollingpaper.paper.model;


import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // new 클래스() 제한
@ToString(callSuper = true)
@EqualsAndHashCode
@Getter
@Entity
@DynamicInsert // null로 들어가야 하는 경우 대응(기본값)
@DynamicUpdate // 변경한 필드만 대응
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
    public Paper(Long paperId, String paperTitle, String theme, String paperUrl, String deleteYn, String userId, Timestamp dueDate, Timestamp createdAt, Timestamp updatedAt) {
        this.paperId = paperId;
        this.paperTitle = paperTitle;
        this.theme = theme;
        this.paperUrl = paperUrl;
        this.deleteYn = deleteYn;
        this.userId = userId;
        this.dueDate = dueDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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


    public PutPaperRes toPutPaperRes() {
        return new PutPaperRes.PutPaperResBuilder().
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

    public void softRemovePaper() {
        this.deleteYn = "Y";
    }

}