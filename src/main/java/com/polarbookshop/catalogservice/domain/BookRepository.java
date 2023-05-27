package com.polarbookshop.catalogservice.domain;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository
{
    Iterable<Book> findAll();

    Optional<Book> findByISBN(String isbn);

    boolean existsByISBN(String isbn);

    Book save(Book book);

    void deleteByISBN(String isbn);
}
