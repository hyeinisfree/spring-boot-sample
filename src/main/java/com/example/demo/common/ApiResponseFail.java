package com.example.demo.common;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
public class ApiResponseFail<T> {
    @Builder.Default
    private boolean success = false;
    @Builder.Default
    private int code = 400;
    private String message;
    private T errors;

    public static <T> ResponseEntity<ApiResponseFail<T>> fail(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(ApiResponseFail.<T>builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build());
    }

    public static <T> ResponseEntity<ApiResponseFail<T>> fail(ErrorCode errorCode, T errors) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(ApiResponseFail.<T>builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .errors(errors)
                        .build());
    }
}
