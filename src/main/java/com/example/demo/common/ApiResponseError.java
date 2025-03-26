package com.example.demo.common;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
public class ApiResponseError<T> {
    @Builder.Default
    private boolean success = false;
    @Builder.Default
    private int code = 500;
    private String message;
    private T errors;

    public static <T> ResponseEntity<ApiResponseError<T>> error(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(ApiResponseError.<T>builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build());
    }
}
