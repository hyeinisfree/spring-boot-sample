package com.example.demo.repository;

import com.example.demo.domain.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository {

    private final List<Author> authors = new ArrayList<>();
    private Long id = 1L;

    public List<Author> findAll() {
        return authors;
    }

    public Optional<Author> findById(Long id) {
        return authors.stream().filter(author -> author.getId().equals(id)).findFirst();
    }

    public void save(Author author) {
        author.setId(id++);
        authors.add(author);
    }

    public void delete(Long id) {
        authors.removeIf(author -> author.getId().equals(id));
    }}
