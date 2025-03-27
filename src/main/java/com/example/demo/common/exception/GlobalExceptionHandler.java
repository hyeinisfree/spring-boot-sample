package com.example.demo.common.exception;

import com.example.demo.common.ApiResponseError;
import com.example.demo.common.ApiResponseFail;
import com.example.demo.common.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponseFail<Void>> handleCustomException(CustomException ex) {
        log.warn("[handleCustomException] ErrorCode::{} - {}", ex.getErrorCode(), ex.getErrorCode().getMessage(), ex);
        return ApiResponseFail.fail(ex.getErrorCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseFail<Map<String, String>>> handleValidationException(MethodArgumentNotValidException ex) {
        log.warn("[handleValidationException] {}", ex.getClass().getName(), ex);
        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        DefaultMessageSourceResolvable::getDefaultMessage,
                        (existing, replacement) -> existing
                ));

        return ApiResponseFail.fail(ErrorCode.INVALID_INPUT_VALUE, errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponseFail<Void>> handleEnumTypeMismatch(HttpMessageNotReadableException ex) {
        log.warn("[handleEnumTypeMismatch] {}", ex.getClass().getName(), ex);
        return ApiResponseFail.fail(ErrorCode.INVALID_INPUT_VALUE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseError<Void>> handleException(Exception ex) {
        log.error("[handleException] {}", ex.getClass().getName(), ex);
        return ApiResponseError.error(ErrorCode.INTERNAL_SERVER_ERROR);
    }

}
