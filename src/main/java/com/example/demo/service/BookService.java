package com.example.demo.service;

import com.example.demo.domain.Book;
import com.example.demo.dto.BookRequestDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final List<Book> books = new ArrayList<>();
    private Long nextId = 1L;

    public List<Book> getAllBooks() {
        return books;
    }

    public Optional<Book> getBookById(Long id) {
        return books.stream().filter(book -> book.getId().equals(id)).findFirst();
    }

    public Book createBook(BookRequestDto request) {
        Book book = request.toBook(nextId++);
        books.add(book);
        return book;
    }

    public Optional<Book> updateBook(Long id, BookRequestDto request) {
        return getBookById(id).map(book -> {
            book.setTitle(request.getTitle());
            book.setAuthor(request.getAuthor());
            return book;
        });
    }

    public boolean deleteBook(Long id) {
        return books.removeIf(book -> book.getId().equals(id));
    }
}
