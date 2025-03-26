package com.example.demo.service;


import com.example.demo.common.CustomException;
import com.example.demo.common.ErrorCode;
import com.example.demo.domain.Author;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.service.dto.AuthorServiceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.AUTHOR_NOT_FOUND));
    }

    public Author createAuthor(AuthorServiceDto serviceDto) {
        Author author = serviceDto.toAuthor();
        authorRepository.save(author);
        return author;
    }

    public void deleteAuthor(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.AUTHOR_NOT_FOUND));
        authorRepository.delete(id);
    }
}
