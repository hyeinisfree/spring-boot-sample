package com.example.demo.service;


import com.example.demo.domain.Author;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.service.dto.AuthorServiceDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public Author createAuthor(AuthorServiceDto serviceDto) {
        Author author = serviceDto.toAuthor();
        authorRepository.save(author);
        return author;
    }

    public void deleteAuthor(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        authorRepository.delete(id);
    }
}
