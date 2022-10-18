package com.memorymakerpeople.memoryrollingpaper.sticker.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostStickerReq {
    private Long stickerId;
    private int stickerSize;
    private String type;
    private float rotate;
    private float x;
    private float y;
    private float scale;
    private Long paperId;
    private String requestType;
}
