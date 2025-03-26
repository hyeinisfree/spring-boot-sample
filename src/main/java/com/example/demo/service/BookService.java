package com.example.demo.service;

import com.example.demo.common.CustomException;
import com.example.demo.common.ErrorCode;
import com.example.demo.domain.Author;
import com.example.demo.domain.Book;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.dto.BookServiceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.BOOK_NOT_FOUND));
    }

    public Book createBook(BookServiceDto serviceDto) {
        Author author = authorRepository.findById(serviceDto.getAuthorId()).orElseThrow(() -> new CustomException(ErrorCode.AUTHOR_NOT_FOUND));
        Book book = serviceDto.toBook(author);
        bookRepository.save(book);
        return book;
    }

    public Book updateBook(Long id, BookServiceDto serviceDto) {
        Author author = authorRepository.findById(serviceDto.getAuthorId()).orElseThrow(() -> new CustomException(ErrorCode.AUTHOR_NOT_FOUND));
        Book book = bookRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.BOOK_NOT_FOUND));
        book.updateBook(author, serviceDto.getTitle(), serviceDto.getSubtitle(), serviceDto.getGenre(), serviceDto.getIsSeries(), serviceDto.getPublishedDate());
        return book;
    }

    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.BOOK_NOT_FOUND));
        bookRepository.delete(id);
    }
}
