package com.example.demo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class Author {
    @Setter
    private Long id;
    private String name;
    private String nationality;
    private Integer age;

}
