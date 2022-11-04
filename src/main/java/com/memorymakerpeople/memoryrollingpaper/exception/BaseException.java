package com.memorymakerpeople.memoryrollingpaper.exception;

import com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseException extends Exception {
    private BaseResponseStatus status;
}
