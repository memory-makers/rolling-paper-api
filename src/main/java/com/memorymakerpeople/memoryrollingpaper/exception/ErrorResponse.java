package com.memorymakerpeople.memoryrollingpaper.exception;

import com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponse {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String error;
    private final String code;
    private final String message;

    public static ResponseEntity<ErrorResponse> toResponseEntity(BaseResponseStatus errorCode) {
        return ResponseEntity
                .status(errorCode.getCode())
                .body(ErrorResponse.builder()
                        .status(errorCode.getCode())
                        .error(errorCode.name())
                        .message(errorCode.getMessage())
                        .build()
                );
    }
}
