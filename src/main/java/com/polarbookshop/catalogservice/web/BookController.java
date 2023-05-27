package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
public class BookController
{
    private final BookService bookService;

    public BookController(final BookService bookService)
    {
        this.bookService = bookService;
    }

    @GetMapping
    public Iterable<Book> get()
    {
        return bookService.viewBookList();
    }

    @GetMapping("{isbn}")
    public Book getByISBN(@PathVariable String isbn)
    {
        return bookService.viewBookDetails(isbn);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book post(@Valid @RequestBody Book book)
    {
        Book savedBook = bookService.addBookToCatalog(book);
        return savedBook;
    }

    @DeleteMapping("{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String isbn)
    {
        bookService.removeBookFromCatalog(isbn);
    }

    @PutMapping("{isbn}")
    public Book put(@PathVariable String isbn, @Valid @RequestBody Book book)
    {
        return bookService.editBookDetails(isbn, book);
    }
}
