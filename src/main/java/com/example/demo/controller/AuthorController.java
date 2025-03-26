package com.example.demo.controller;

import com.example.demo.common.ApiResponse;
import com.example.demo.common.ResponseCode;
import com.example.demo.common.ResponseList;
import com.example.demo.common.ResponseResult;
import com.example.demo.controller.dto.AuthorRequestDto;
import com.example.demo.controller.dto.AuthorResponseDto;
import com.example.demo.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<ApiResponse<ResponseList<AuthorResponseDto>>> getAllAuthors() {
        return ApiResponse.successList(ResponseCode.OK, authorService.getAllAuthors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AuthorResponseDto>> getAuthorById(@PathVariable Long id) {
        return ApiResponse.success(ResponseCode.OK, authorService.getAuthorById(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AuthorResponseDto>> createAuthor(@RequestBody @Valid AuthorRequestDto request) {
        return ApiResponse.success(ResponseCode.CREATED, authorService.createAuthor(request.toServiceDto()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<ResponseResult<Boolean>>> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ApiResponse.successResult(ResponseCode.OK, true);
    }
}
