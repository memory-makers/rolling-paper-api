package com.memorymakerpeople.memoryrollingpaper.card.model;
import com.memorymakerpeople.memoryrollingpaper.sticker.model.StickerRes;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // new 클래스() 제한
@ToString(callSuper = true)
@EqualsAndHashCode
@Getter
@Entity
@DynamicInsert // null로 들어가야 하는 경우 대응(기본값)
@DynamicUpdate // 변경한 필드만 대응
@Table(name = "tbl_card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;
    private String cardText;
    private String cardColor;
    private Long paperId;
    private String fontStyle;
    private String fontColor;
    private String cardWriter;

    @Builder
    public Card(Long cardId, String cardText, String cardColor, Long paperId, String fontStyle, String fontColor,String  cardWriter) {
        this.cardId = cardId;
        this.cardText = cardText;
        this.cardColor = cardColor;
        this.paperId = paperId;
        this.fontStyle = fontStyle;
        this.fontColor = fontColor;
        this.cardWriter = cardWriter;
    }

    public CardRes GetCardRes() {
        return new CardRes.CardResBuilder()
                .cardId(this.cardId)
                .cardText(this.cardText)
                .cardColor(this.cardColor)
                .paperId(this.paperId)
                .fontStyle(this.fontStyle)
                .fontColor(this.fontColor)
                .cardWriter(this.cardWriter)
                .build();
    }
}
