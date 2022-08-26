package com.memorymakerpeople.memoryrollingpaper.paper.model;

import com.memorymakerpeople.memoryrollingpaper.card.model.Card;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class postPaperResponse {
    private Paper paper;
    private Integer status;

}
