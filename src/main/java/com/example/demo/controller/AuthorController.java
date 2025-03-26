package com.example.demo.controller;

import com.example.demo.common.ApiResponse;
import com.example.demo.common.ResponseList;
import com.example.demo.common.ResponseResult;
import com.example.demo.controller.dto.AuthorRequestDto;
import com.example.demo.domain.Author;
import com.example.demo.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<ApiResponse<ResponseList<Author>>> getAllAuthors() {
        return ApiResponse.successList(authorService.getAllAuthors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Author>> getAuthorById(@PathVariable Long id) {
        Author author = authorService.getAuthorById(id);
        return ApiResponse.success(author);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Author>> createAuthor(@RequestBody AuthorRequestDto request) {
        Author author = authorService.createAuthor(request.toServiceDto());
        return ApiResponse.success(author);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<ResponseResult<Boolean>>> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ApiResponse.successResult(true);
    }
}
