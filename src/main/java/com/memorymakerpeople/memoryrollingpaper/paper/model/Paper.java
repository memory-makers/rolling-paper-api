package com.memorymakerpeople.memoryrollingpaper.paper.model;

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
}