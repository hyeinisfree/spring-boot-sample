package com.example.demo.common;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
@Builder
public class ApiResponse<T> {
    @Builder.Default
    private boolean success = true;
    @Builder.Default
    private int code = 200;
    private String message;
    private T data;

    public static <T> ResponseEntity<ApiResponse<T>> success(ResponseCode responseCode) {
        return ResponseEntity
                .status(responseCode.getHttpStatus())
                .body(
                        ApiResponse.<T>builder()
                                .code(responseCode.getCode())
                                .message(responseCode.getMessage())
                                .build()
                );
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(ResponseCode responseCode, T data) {
        return ResponseEntity
                .status(responseCode.getHttpStatus())
                .body(
                        ApiResponse.<T>builder()
                                .code(responseCode.getCode())
                                .message(responseCode.getMessage())
                                .data(data)
                                .build()
                );
    }

    public static <T> ResponseEntity<ApiResponse<ResponseResult<T>>> successResult(ResponseCode responseCode, T data) {
        return ResponseEntity
                .status(responseCode.getHttpStatus())
                .body(
                        ApiResponse.<ResponseResult<T>>builder()
                                .code(responseCode.getCode())
                                .message(responseCode.getMessage())
                                .data(new ResponseResult<>(data))
                                .build()
                );
    }

    public static <T> ResponseEntity<ApiResponse<ResponseList<T>>> successList(ResponseCode responseCode, List<T> data) {
        return ResponseEntity
                .status(responseCode.getHttpStatus())
                .body(
                        ApiResponse.<ResponseList<T>>builder()
                                .code(responseCode.getCode())
                                .message(responseCode.getMessage())
                                .data(new ResponseList<>(data))
                                .build()
                );
    }

    public static <T> ResponseEntity<ApiResponse<ResponsePage<T>>> successPage(ResponseCode responseCode, Page<T> data) {
        return ResponseEntity
                .status(responseCode.getHttpStatus())
                .body(
                        ApiResponse.<ResponsePage<T>>builder()
                                .code(responseCode.getCode())
                                .message(responseCode.getMessage())
                                .data(ResponsePage.of(data))
                                .build()
                );
    }

}
