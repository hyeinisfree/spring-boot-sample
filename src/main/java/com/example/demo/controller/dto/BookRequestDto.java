package com.example.demo.controller.dto;

import com.example.demo.domain.Genre;
import com.example.demo.service.dto.BookServiceDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class BookRequestDto {
    private Long authorId;

    @NotBlank(message = "제목은 필수입니다.")
    @Size(max = 50, message = "제목은 50자 이내여야 합니다.")
    private String title;

    @Size(max = 100, message = "부제목은 100자 이내여야 합니다.")
    private String subtitle;

    private Genre genre;
    private Boolean isSeries;
    private LocalDate publishedDate;

    public BookServiceDto toServiceDto() {
        return BookServiceDto.builder()
                .authorId(authorId)
                .title(title)
                .subtitle(subtitle)
                .genre(genre)
                .isSeries(isSeries)
                .publishedDate(publishedDate)
                .build();
    }
}
