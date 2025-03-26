package com.example.demo.service;

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
        return bookRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public Book createBook(BookServiceDto serviceDto) {
        Author author = authorRepository.findById(serviceDto.getAuthorId()).orElseThrow(IllegalArgumentException::new);
        Book book = serviceDto.toBook(author);
        bookRepository.save(book);
        return book;
    }

    public Book updateBook(Long id, BookServiceDto serviceDto) {
        Author author = authorRepository.findById(serviceDto.getAuthorId()).orElseThrow(IllegalArgumentException::new);
        Book book = bookRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        book.updateBook(author, serviceDto.getTitle(), serviceDto.getSubtitle(), serviceDto.getGenre(), serviceDto.getIsSeries(), serviceDto.getPublishedDate());
        return book;
    }

    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        bookRepository.delete(id);
    }
}
