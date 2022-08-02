package com.memorymakerpeople.memoryrollingpaper.domain;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tbl_card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardId;
    private String cardText;
    private String cardColor;
    private String paperId;
    private String fontStyle;
    private String fontColor;
}
