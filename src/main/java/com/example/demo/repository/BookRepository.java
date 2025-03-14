package com.example.demo.repository;

import com.example.demo.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository {

    private final List<Book> books = new ArrayList<>();
    private Long id = 1L;

    public List<Book> findAll() {
        return books;
    }

    public Optional<Book> findById(Long id) {
        return books.stream().filter(book -> book.getId().equals(id)).findFirst();
    }

    public void save(Book book) {
        book.setId(id++);
        books.add(book);
    }

    public void delete(Long id) {
        books.removeIf(book -> book.getId().equals(id));
    }
}
