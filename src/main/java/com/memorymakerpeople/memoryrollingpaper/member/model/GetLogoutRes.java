package com.memorymakerpeople.memoryrollingpaper.member.model;

import com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetLogoutRes {
    private String state;
    private BaseResponseStatus status;
}
