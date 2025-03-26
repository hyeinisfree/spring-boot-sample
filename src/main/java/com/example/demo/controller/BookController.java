package com.example.demo.controller;

import com.example.demo.common.ApiResponse;
import com.example.demo.common.ResponseCode;
import com.example.demo.common.ResponseList;
import com.example.demo.common.ResponseResult;
import com.example.demo.controller.dto.BookRequestDto;
import com.example.demo.controller.dto.BookResponseDto;
import com.example.demo.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<ApiResponse<ResponseList<BookResponseDto>>> getAllBooks() {
        return ApiResponse.successList(ResponseCode.OK, bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BookResponseDto>> getBookById(@PathVariable Long id) {
        return ApiResponse.success(ResponseCode.OK, bookService.getBookById(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BookResponseDto>> createBook(@RequestBody @Valid BookRequestDto request) {
        return ApiResponse.success(ResponseCode.CREATED, bookService.createBook(request.toServiceDto()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BookResponseDto>> updateBook(@PathVariable Long id, @RequestBody @Valid BookRequestDto request) {
        return ApiResponse.success(ResponseCode.OK, bookService.updateBook(id, request.toServiceDto()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<ResponseResult<Boolean>>> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ApiResponse.successResult(ResponseCode.OK, true);
    }
}
