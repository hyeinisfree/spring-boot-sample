package com.example.demo.controller;

import com.example.demo.common.ApiResponse;
import com.example.demo.common.ResponseCode;
import com.example.demo.common.ResponseList;
import com.example.demo.common.ResponseResult;
import com.example.demo.controller.dto.BookCreateRequestDto;
import com.example.demo.controller.dto.BookPatchRequestDto;
import com.example.demo.controller.dto.BookPutRequestDto;
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
    public ResponseEntity<ApiResponse<BookResponseDto>> createBook(@RequestBody @Valid BookCreateRequestDto request) {
        return ApiResponse.success(ResponseCode.CREATED, bookService.createBook(request.toServiceDto()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BookResponseDto>> putUpdateBook(@PathVariable Long id, @RequestBody @Valid BookPutRequestDto request) {
        return ApiResponse.success(ResponseCode.OK, bookService.putUpdateBook(id, request.toServiceDto()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<BookResponseDto>> patchUpdateBook(@PathVariable Long id, @RequestBody @Valid BookPatchRequestDto request) {
        return ApiResponse.success(ResponseCode.OK, bookService.patchUpdateBook(id, request.toServiceDto()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<ResponseResult<Boolean>>> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ApiResponse.successResult(ResponseCode.OK, true);
    }
}
