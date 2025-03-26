package com.example.demo.common;

import com.example.demo.common.ApiResponse;
import com.example.demo.common.CustomException;
import com.example.demo.common.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse> handleCustomException(CustomException ex) {
        return ApiResponse.fail(ex.getErrorCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception ex) {
        return ApiResponse.fail(ErrorCode.INTERNAL_SERVER_ERROR);
    }

}
