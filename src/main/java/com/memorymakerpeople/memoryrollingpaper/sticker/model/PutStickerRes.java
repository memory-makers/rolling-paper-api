package com.memorymakerpeople.memoryrollingpaper.sticker.model;

import com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PutStickerRes {
    private Sticker sticker;
    private BaseResponseStatus status;
}
