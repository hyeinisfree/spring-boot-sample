package com.example.demo.repository;

import com.example.demo.domain.Book;
import com.example.demo.domain.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class BookRepositoryTest {

    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository = new BookRepository();
    }
    
    @Test
    void 책_전체_조회_성공() {
        bookRepository.save(Book.builder()
                .title("책1")
                .subtitle("부제1")
                .genre(Genre.FICTION)
                .isSeries(false)
                .publishedDate(LocalDate.now())
                .build());

        bookRepository.save(Book.builder()
                .title("책2")
                .subtitle("부제2")
                .genre(Genre.NONFICTION)
                .isSeries(true)
                .publishedDate(LocalDate.now())
                .build());

        assertThat(bookRepository.findAll())
                .hasSize(2)
                .extracting(Book::getTitle)
                .containsExactly("책1", "책2");
    }

    @Test
    void 책_ID로_조회_성공() {
        Book saved = bookRepository.save(Book.builder()
                .title("Java")
                .subtitle("입문")
                .genre(Genre.NONFICTION)
                .isSeries(false)
                .publishedDate(LocalDate.of(2024, 5, 1))
                .build());

        Optional<Book> result = bookRepository.findById(saved.getId());

        assertThat(result).isPresent();
        assertThat(result.get().getTitle()).isEqualTo("Java");
    }

    @Test
    void 책_저장_성공() {
        Book book = Book.builder()
                .title("테스트 책")
                .subtitle("테스트 부제")
                .genre(Genre.FICTION)
                .isSeries(false)
                .publishedDate(LocalDate.now())
                .build();

        Book saved = bookRepository.save(book);

        assertThat(saved.getId()).isNotNull();
        assertThat(bookRepository.findAll()).hasSize(1);
    }

    @Test
    void 책_삭제_성공() {
        Book saved = bookRepository.save(Book.builder()
                .title("삭제될 책")
                .subtitle("테스트")
                .genre(Genre.FICTION)
                .isSeries(false)
                .publishedDate(LocalDate.now())
                .build());

        bookRepository.delete(saved.getId());

        assertThat(bookRepository.findById(saved.getId())).isEmpty();
        assertThat(bookRepository.findAll()).isEmpty();
    }
}
