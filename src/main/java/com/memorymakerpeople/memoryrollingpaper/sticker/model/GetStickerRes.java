package com.memorymakerpeople.memoryrollingpaper.sticker.model;

import com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetStickerRes {
    private Sticker sticker;
    private BaseResponseStatus status;
}
