package com.example.demo.concurrency;

import com.example.demo.domain.Author;
import com.example.demo.domain.Book;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookRepositoryConcurrencyTest {

    private BookRepository bookRepository;

    private AuthorRepository authorRepository;

    Author author = Author.builder()
            .name("name")
            .nationality("korea")
            .age(20)
            .build();

    @BeforeEach
    void setUp() {
        bookRepository = new BookRepository();
        authorRepository = new AuthorRepository();
        authorRepository.save(author);
    }

    @Test
    void save_동시호출_id중복_발생_테스트() throws InterruptedException {
        int threadCount = 1000;
        ExecutorService executor = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                try {
                    Book book = Book.builder()
                            .title("Book " + UUID.randomUUID())
                            .author(authorRepository.findById(author.getId()).orElseThrow())
                            .build();
                    bookRepository.save(book);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        long idCount = bookRepository.findAll().stream()
                .map(Book::getId).count();
        long distinctIdCount = bookRepository.findAll().stream()
                .map(Book::getId).distinct().count();

        assertEquals(idCount, distinctIdCount);
    }

    @Test
    void save_동시호출_데이터손실_발생_테스트() throws InterruptedException {
        int threadCount = 1000;
        ExecutorService executor = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                try {
                    Book book = Book.builder()
                            .title("Book " + UUID.randomUUID())
                            .author(authorRepository.findById(author.getId()).orElseThrow())
                            .build();
                    bookRepository.save(book);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        assertEquals(1000, bookRepository.findAll().size());
    }

    @Test
    void delete_동시호출_삭제누락_발생_테스트() throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            bookRepository.save(
                    Book.builder()
                            .title("Book " + UUID.randomUUID())
                            .author(authorRepository.findById(author.getId()).orElseThrow())
                            .build()
            );
        }

        int threadCount = 1000;
        ExecutorService executor = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (long i = 1; i <= threadCount; i++) {
            final long idToDelete = i;
            executor.submit(() -> {
                try {
                    bookRepository.delete(idToDelete);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        assertEquals(0, bookRepository.findAll().size());
    }

    @Test
    void findAll_순회중_save시_ConcurrentModificationException_발생_테스트() throws InterruptedException {
        for (int i = 0; i < 500; i++) {
            bookRepository.save(
                    Book.builder()
                            .title("Book " + UUID.randomUUID())
                            .author(authorRepository.findById(author.getId()).orElseThrow())
                            .build()
            );
        }

        ExecutorService executor = Executors.newFixedThreadPool(2);
        CountDownLatch latch = new CountDownLatch(2);

        executor.submit(() -> {
            try {
                for (Book book : bookRepository.findAll()) {
                    Thread.sleep(1); // 일부러 느리게
                }
            } catch (ConcurrentModificationException e) {
                System.out.println("💥 ConcurrentModificationException 발생!");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                latch.countDown();
            }
        });

        executor.submit(() -> {
            try {
                Thread.sleep(5); // 순회 중간에 삽입
                bookRepository.save(
                        Book.builder()
                                .title("Book " + UUID.randomUUID())
                                .author(authorRepository.findById(author.getId()).orElseThrow())
                                .build()
                );
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                latch.countDown();
            }
        });

        latch.await();
    }

    @Test
    void findAll_순회중_delete시_ConcurrentModificationException_발생_테스트() throws InterruptedException {
        for (int i = 0; i < 500; i++) {
            bookRepository.save(
                    Book.builder()
                            .title("Book " + UUID.randomUUID())
                            .author(authorRepository.findById(author.getId()).orElseThrow())
                            .build()
            );
        }

        ExecutorService executor = Executors.newFixedThreadPool(2);
        CountDownLatch latch = new CountDownLatch(2);

        executor.submit(() -> {
            try {
                for (Book book : bookRepository.findAll()) {
                    Thread.sleep(1); // 느리게 순회
                }
            } catch (ConcurrentModificationException e) {
                System.out.println("💥 삭제 중 충돌 발생!");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                latch.countDown();
            }
        });

        executor.submit(() -> {
            try {
                Thread.sleep(5); // 순회 중간에 삭제
                bookRepository.delete(1L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                latch.countDown();
            }
        });

        latch.await();
    }
}