package com.example.demo.controller;

import com.example.demo.controller.dto.BookRequestDto;
import com.example.demo.controller.dto.BookResponseDto;
import com.example.demo.domain.Genre;
import com.example.demo.service.BookService;
import com.example.demo.service.dto.BookServiceDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private BookService bookService;

    @Test
    void 책_전체_조회_성공() throws Exception {
        // given
        given(bookService.getAllBooks()).willReturn(List.of());

        // when & then
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.data.contents").isArray());
    }

    @Test
    void 책_단건_조회_성공() throws Exception {
        // given
        BookResponseDto responseDto = BookResponseDto.builder()
                .id(1L)
                .author("작가")
                .title("제목")
                .subtitle("부제")
                .genre(Genre.FICTION)
                .isSeries(true)
                .publishedDate(LocalDate.now())
                .build();

        given(bookService.getBookById(1L)).willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.title").value("제목"));
    }

    @Test
    void 책_등록_성공() throws Exception {
        // given
        BookRequestDto requestDto =  BookRequestDto.builder()
                .authorId(1L)
                .title("제목")
                .subtitle("부제")
                .genre(Genre.FICTION)
                .isSeries(true)
                .publishedDate(LocalDate.now())
                .build();
        BookResponseDto responseDto = BookResponseDto.builder()
                .id(1L)
                .author("작가")
                .title("제목")
                .subtitle("부제")
                .genre(Genre.FICTION)
                .isSeries(true)
                .publishedDate(LocalDate.now())
                .build();

        given(bookService.createBook(any(BookServiceDto.class))).willReturn(responseDto);

        // when & then
        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.code").value(201))
                .andExpect(jsonPath("$.message").value("자원이 생성되었습니다."))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.title").value("제목"))
                .andExpect(jsonPath("$.data.author").value("작가"));
    }


    @Test
    void 책_등록_실패() throws Exception {
        // given: 필수 필드 누락 (예: title이 null)
        BookRequestDto invalidDto = BookRequestDto.builder()
                .authorId(1L)
                .title(null) // title 누락
                .subtitle("부제")
                .genre(Genre.FICTION)
                .isSeries(true)
                .publishedDate(LocalDate.now())
                .build();

        // when & then
        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.errors").exists());
    }




    @Test
    void 책_수정_성공() throws Exception {
        // given
        BookRequestDto requestDto = BookRequestDto.builder()
                .authorId(1L)
                .title("수정된 제목")
                .subtitle("수정된 부제")
                .genre(Genre.FICTION)
                .isSeries(false)
                .publishedDate(LocalDate.now())
                .build();
        BookResponseDto responseDto = BookResponseDto.builder()
                .id(1L)
                .author("작가")
                .title("수정된 제목")
                .subtitle("수정된 부제")
                .genre(Genre.FICTION)
                .isSeries(false)
                .publishedDate(LocalDate.now())
                .build();

        given(bookService.updateBook(eq(1L), any(BookServiceDto.class))).willReturn(responseDto);

        // when & then
        mockMvc.perform(put("/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.title").value("수정된 제목"));
    }

    @Test
    void 책_삭제_성공() throws Exception {
        // when & then
        mockMvc.perform(delete("/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.result").value(true));
    }
}
