package com.example.demo.controller;

import com.example.demo.common.ApiResponse;
import com.example.demo.common.ResponseList;
import com.example.demo.common.ResponseResult;
import com.example.demo.controller.dto.BookRequestDto;
import com.example.demo.domain.Book;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<ApiResponse<ResponseList<Book>>> getAllBooks() {
        return ApiResponse.successList(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Book>> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        return ApiResponse.success(book);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Book>> createBook(@RequestBody BookRequestDto request) {
        Book book = bookService.createBook(request.toServiceDto());
        return ApiResponse.success(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Book>> updateBook(@PathVariable Long id, @RequestBody BookRequestDto request) {
        Book book = bookService.updateBook(id, request.toServiceDto());
        return ApiResponse.success(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<ResponseResult<Boolean>>> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ApiResponse.successResult(true);
    }
}
