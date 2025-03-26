package com.example.demo.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ResponseResult<T> {
    private final T result;
}
