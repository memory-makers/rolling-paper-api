package com.memorymakerpeople.memoryrollingpaper.card.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostCardReq {
    @NotEmpty
    private String cardText;
    @NotBlank
    private String cardColor;
    @NotBlank
    private Long paperId;
    @NotEmpty
    private String fontStyle;
    @NotEmpty
    private String fontColor;
    @NotEmpty
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
