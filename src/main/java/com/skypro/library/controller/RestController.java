package com.skypro.library.controller;

import com.skypro.library.dto.BookDTO;
import com.skypro.library.model.Book;
import com.skypro.library.service.BookService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("skypro")
public class RestController {

    private BookService bookService;

    public RestController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/api/book")
    public Book addBook(@RequestBody Book book) {
        bookService.addBook(book);
        return book;
    }

    @GetMapping("/api/book")
    public List<BookDTO> getBooks() {
        return bookService.getBooks();
    }

    @PutMapping("/api/book")
    public Book updateBook(@RequestBody Book book) {
        bookService.updateBook(book);
        return book;
    }

    @DeleteMapping("/api/book")
    public String deleteBook(@RequestParam String isbn) {
        bookService.deleteBook(isbn);
        return " isbn= " + isbn + " удалён";
    }

}

