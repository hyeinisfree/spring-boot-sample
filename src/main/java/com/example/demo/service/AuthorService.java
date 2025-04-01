package com.example.demo.service;


import com.example.demo.common.ErrorCode;
import com.example.demo.common.exception.CustomException;
import com.example.demo.controller.dto.AuthorResponseDto;
import com.example.demo.domain.Author;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.service.dto.AuthorServiceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<AuthorResponseDto> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream().map(AuthorResponseDto::of).toList();
    }

    public AuthorResponseDto getAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.AUTHOR_NOT_FOUND));
        return AuthorResponseDto.of(author);
    }

    @Transactional
    public AuthorResponseDto createAuthor(AuthorServiceDto serviceDto) {
        Author savedAuthor = authorRepository.save(serviceDto.toAuthor());
        return AuthorResponseDto.of(savedAuthor);
    }

    @Transactional
    public void deleteAuthor(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.AUTHOR_NOT_FOUND));
        authorRepository.delete(author);
    }
}
