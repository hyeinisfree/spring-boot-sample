package com.example.demo.common;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
@Builder
public class ApiResponse<T> {
    private boolean success;
    private int code;
    private String message;
    private T data;

    public static ResponseEntity<ApiResponse> success() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ApiResponse.builder()
                                .success(true)
                                .code(200)
                                .message("요청이 성공했습니다.")
                                .data(null)
                                .build()
                );
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(T data) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ApiResponse.<T>builder()
                        .success(true)
                        .code(200)
                        .message("요청이 성공했습니다.")
                        .data(data)
                        .build()
                );
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(String message, T data) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ApiResponse.<T>builder()
                                .success(true)
                                .code(200)
                                .message(message)
                                .data(data)
                                .build()
                );
    }

    public static <T> ResponseEntity<ApiResponse<ResponseResult<T>>> successResult(T data) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ApiResponse.<ResponseResult<T>>builder()
                                .success(true)
                                .code(200)
                                .message("요청이 성공했습니다.")
                                .data(new ResponseResult<>(data))
                                .build()
                );
    }

    public static <T> ResponseEntity<ApiResponse<ResponseList<T>>> successList(List<T> data) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ApiResponse.<ResponseList<T>>builder()
                                .success(true)
                                .code(200)
                                .message("요청이 성공했습니다.")
                                .data(new ResponseList<>(data))
                                .build()
                );
    }
}
