package com.example.demo.repository;

import com.example.demo.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class BookRepository {

    // 1. Collections.synchronizedList() + AtomicLong을 통한 동시성 문제 해결
    private final List<Book> books = Collections.synchronizedList(new ArrayList<>());
    private final AtomicLong id = new AtomicLong(1);

    public List<Book> findAll() {
        // 💡 주의! 순회할 때는 수동으로 synchronized 블록을 써야 예외 방지 가능
        synchronized (books) {
            return new ArrayList<>(books); // 복사본 반환 (외부 수정 방지)
        }
    }

    public Optional<Book> findById(Long id) {
        synchronized (books) {
            return books.stream()
                    .filter(book -> book.getId().equals(id))
                    .findFirst();
        }
    }

    public Book save(Book book) {
        book.setId(id.getAndIncrement());
        books.add(book);
        return book;
    }

    public void delete(Long id) {
        books.removeIf(book -> book.getId().equals(id));
    }

//    // 2. CopyOnWriteArrayList + AtomicLong을 통한 동시성 문제 해결
//    private final List<Book> books = new CopyOnWriteArrayList<>();
//    private final AtomicLong id = new AtomicLong(1);
//
//    public List<Book> findAll() {
//        return books;
//    }
//
//    public Optional<Book> findById(Long id) {
//        return books.stream().filter(book -> book.getId().equals(id)).findFirst();
//    }
//
//    public Book save(Book book) {
//        book.setId(id.getAndIncrement());
//        books.add(book);
//        return book;
//    }
//
//    public void delete(Long id) {
//        books.removeIf(book -> book.getId().equals(id));
//    }
}
