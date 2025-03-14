package com.example.demo.domain;

import java.time.LocalDate;

public class Book {
    private Long id = 1L;
    private Author author;
    private String title;

    private String subtitle;
    private Genre genre;

    private Boolean isSeries;

    private LocalDate publishedDate;

    public Book(Author author, String title, String subtitle, Genre genre, Boolean isSeries, LocalDate publishedDate) {
        this.author = author;
        this.title = title;
        this.subtitle = subtitle;
        this.genre = genre;
        this.isSeries = isSeries;
        this.publishedDate = publishedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Boolean getIsSeries() {
        return isSeries;
    }

    public void setIsSeries(Boolean series) {
        isSeries = series;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void updateBook(Author author, String title, String subtitle, Genre genre, Boolean isSeries, LocalDate publishedDate) {
        this.author = author;
        this.title = title;
        this.subtitle = subtitle;
        this.genre = genre;
        this.isSeries = isSeries;
        this.publishedDate = publishedDate;
    }
}
