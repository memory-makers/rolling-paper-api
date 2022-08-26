package com.memorymakerpeople.memoryrollingpaper.paper.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tbl_paper")
public class Paper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paperId;
    private String paperTitle;
    private Timestamp dueDt;
    private String theme;
    private String paperUrl;
    private String deleteYn;
    private String openStatus;
    private BigInteger userId;
}