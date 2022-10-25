package com.memorymakerpeople.memoryrollingpaper.card.model;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostCardReq {
    @NotBlank
    private String cardText;
    @NotBlank
    private String cardColor;
    @NotBlank
    @Positive
    private Long paperId;
    @NotBlank
    private String fontStyle;
    @NotBlank
    private String fontColor;
    @NotBlank
    private String cardWriter;


    public Card toEntity() {
        return Card.builder().
                cardText(this.cardText).
                cardColor(this.cardColor).
                paperId(this.paperId).
                fontStyle(this.fontStyle).
                fontColor(this.fontColor).
                cardWriter(this.cardWriter)
                .build();
    }

}
