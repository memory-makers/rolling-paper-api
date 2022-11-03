package com.memorymakerpeople.memoryrollingpaper.exception;

import com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private final BaseResponseStatus errorCode;
}