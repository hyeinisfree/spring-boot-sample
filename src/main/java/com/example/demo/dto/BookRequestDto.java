package com.example.demo.dto;

import com.example.demo.domain.Book;

public class BookRequestDto {
    private String title;
    private String author;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Book toBook(Long id) {
        return new Book(id, title, author);
    }
}
