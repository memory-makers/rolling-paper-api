package com.memorymakerpeople.memoryrollingpaper.paper.model;


import com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetPaperNicknameRes {
    private String nickname;
    private BaseResponseStatus status;
}
