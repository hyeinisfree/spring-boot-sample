package com.example.demo.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ResponseList<T> {
    private final List<T> contents;
}
