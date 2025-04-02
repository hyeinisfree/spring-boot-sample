package com.example.demo.service;

import com.example.demo.common.ErrorCode;
import com.example.demo.common.exception.CustomException;
import com.example.demo.controller.dto.BookResponseDto;
import com.example.demo.domain.Author;
import com.example.demo.domain.Book;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.dto.BookServiceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public List<BookResponseDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(BookResponseDto::of).collect(Collectors.toList());
    }

    public BookResponseDto getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.BOOK_NOT_FOUND));
        return BookResponseDto.of(book);
    }

    @Transactional
    public BookResponseDto createBook(BookServiceDto serviceDto) {
        Author author = authorRepository.findById(serviceDto.getAuthorId()).orElseThrow(() -> new CustomException(ErrorCode.AUTHOR_NOT_FOUND));
        Book savedBook = bookRepository.save(serviceDto.toBook(author));
        return BookResponseDto.of(savedBook);
    }

    @Transactional
    public BookResponseDto putUpdateBook(Long id, BookServiceDto serviceDto) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.BOOK_NOT_FOUND));
        book.putUpdateBook(serviceDto.getTitle(), serviceDto.getSubtitle(), serviceDto.getGenre(), serviceDto.getIsSeries(), serviceDto.getPublishedDate());
        return BookResponseDto.of(book);
    }

    @Transactional
    public BookResponseDto patchUpdateBook(Long id, BookServiceDto serviceDto) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.BOOK_NOT_FOUND));
        book.patchUpdateBook(serviceDto.getTitle(), serviceDto.getSubtitle(), serviceDto.getGenre(), serviceDto.getIsSeries(), serviceDto.getPublishedDate());
        return BookResponseDto.of(book);
    }

    @Transactional
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.BOOK_NOT_FOUND));
        bookRepository.delete(book);
    }
}
