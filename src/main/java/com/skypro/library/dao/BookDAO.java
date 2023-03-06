package com.skypro.library.dao;

import com.skypro.library.model.Book;

import java.util.List;

public interface BookDAO {
    List<Book> getBooks();
    Book getBookByIsbn(String isbn);
    void addBook(Book book);
    void updateBook(Book book);
    void deleteBook(String isbn);

}
