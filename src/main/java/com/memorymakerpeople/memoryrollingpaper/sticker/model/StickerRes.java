package com.memorymakerpeople.memoryrollingpaper.sticker.model;

import lombok.*;

@Getter
@NoArgsConstructor
@ToString
public class StickerRes {
    private Long stickerId;
    private int stickerSize;
    private String type;
    private float rotate;
    private float x;
    private float y;
    private float scale;
    private Long paperId;

    @Builder
    public StickerRes(Long stickerId, int stickerSize, String type, float rotate, float x, float y, float scale, Long paperId) {
        this.stickerId = stickerId;
        this.stickerSize = stickerSize;
        this.type = type;
        this.rotate = rotate;
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.paperId = paperId;
    }

}
