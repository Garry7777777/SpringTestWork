package com.skypro.library.service;

import com.skypro.library.dto.BookDTO;
import com.skypro.library.model.Book;

import java.util.List;

public interface BookService {
    List<BookDTO> getBooks();
    Book getBookByIsbn(String isbn);
    void addBook(Book book);
    void updateBook(Book book);
    void deleteBook(String isbn);

}
