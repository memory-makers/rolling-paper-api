package com.memorymakerpeople.memoryrollingpaper.dto;

import com.memorymakerpeople.memoryrollingpaper.domain.Sticker;
import lombok.Data;

@Data
public class StickerResponseDto extends DefaultResponseDto{
    private Sticker sticker;
}
