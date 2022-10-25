package com.memorymakerpeople.memoryrollingpaper.sticker.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostStickerReq {
    private Long stickerId;
    @NotBlank
    @Positive
    private int stickerSize;
    @NotBlank
    private String type;
    @NotBlank
    private float rotate;
    @NotBlank
    private float x;
    @NotBlank
    private float y;
    @NotBlank
    @Positive
    private float scale;
    @NotBlank
    @Positive
    private Long paperId;
    @NotBlank
    private String requestType;

    @Builder
    public Sticker toEntity() {
        return new Sticker.StickerBuilder().
                stickerId(this.stickerId).
                stickerSize(this.stickerSize).
                type(this.type).
                rotate(this.rotate).
                x(this.x).
                y(this.y).
                scale(this.scale).
                paperId(this.paperId).
                build();

    }
}
