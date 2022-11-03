package com.memorymakerpeople.memoryrollingpaper.exception;

import com.memorymakerpeople.memoryrollingpaper.config.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.TypeMismatchException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public BaseResponse illegalExHandler(IllegalArgumentException e) {
        log.error("[exceptionHandler] REQUEST_ERROR", e);
        return new BaseResponse(REQUEST_ERROR);
    }

    /*@ExceptionHandler
    public ResponseEntity<BaseResponse> typeMismatchExHandler(TypeMismatchException e) {
        log.error("[exceptionHandler] INVALID_TYPE_MISMATCH", e);
        BaseResponse errorResult = new BaseResponse(INVALID_TYPE_MISMATCH);
        return new ResponseEntity(errorResult, HttpStatus.BAD_REQUEST);
    }*/

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public BaseResponse exHandler(Exception e) {
        log.error("[exceptionHandler] SERVER_ERROR", e);
        return new BaseResponse(SERVER_ERROR);
    }

    @ExceptionHandler(value = { CustomException.class })
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        log.error("handleCustomException throw CustomException : {}", e.getErrorCode());
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }
}
