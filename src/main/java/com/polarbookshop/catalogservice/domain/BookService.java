package com.polarbookshop.catalogservice.domain;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class BookService
{
    private final BookRepository bookRepository;

    public BookService(final BookRepository bookRepository)
    {
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> viewBookList()
    {
        return bookRepository.findAll();
    }

    public Book viewBookDetails(String isbn)
    {
        return bookRepository.findByISBN(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
    }

    public Book addBookToCatalog(Book book)
    {
         if(bookRepository.existsByISBN(book.isbn()))
         {
             throw new BookAlreadyExistsException(book.isbn());
         }

         return bookRepository.save(book);
    }

    public void removeBookFromCatalog(String isbn)
    {
        bookRepository.deleteByISBN(isbn);
    }

    public Book editBookDetails(String isbn, Book book)
    {
        return bookRepository.findByISBN(isbn).map(existingBook -> {
            var bookToUpdate = new Book(existingBook.isbn(), book.title(), book.author(), book.price());
            return bookRepository.save(bookToUpdate);
        }).orElseGet(() -> bookRepository.save(book));
    }
}
