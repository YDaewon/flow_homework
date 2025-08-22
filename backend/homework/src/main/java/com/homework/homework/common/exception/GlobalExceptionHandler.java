package com.homework.homework.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.homework.homework.common.response.BaseResponse;
import com.homework.homework.common.response.BaseResponseStatus;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BaseResponse<BaseResponseStatus>> handleBusinessException(
        BusinessException e) {
        BaseResponseStatus status = e.getBaseResponseStatus();
        String logMessage = String.format("""
            ⚠️ [BusinessException 발생]
            📍 예외 코드: %s
            ❗ 예외 메시지: %s
            """, status.getCode(), status.getMessage());

        log.error("\n{}", logMessage);

        return ResponseEntity.status(e.getBaseResponseStatus().getHttpStatus())
            .body(new BaseResponse<>(e.getBaseResponseStatus()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        String logMessage = String.format("""
            ___________________ ⚠️ [Exception 발생] ________________________
            ❗ 예외 메시지: %s
            ___________________ ⚠️ [Exception 종료] ________________________
            """, e.getMessage());

        log.error("\n{}", logMessage);

        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
