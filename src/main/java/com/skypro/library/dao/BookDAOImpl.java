package com.skypro.library.dao;

import com.skypro.library.model.Book;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {

    private JdbcTemplate jdbcTemplate;

    public BookDAOImpl( JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> getBooks() {
        return jdbcTemplate.query("SELECT * FROM books", new BeanPropertyRowMapper<>(Book.class));
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        return jdbcTemplate.query("SELECT * FROM books WHERE isbn = ?", new Object[]{isbn},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    @Override
    public void addBook(Book book) {
        jdbcTemplate.update("INSERT INTO books VALUES (?, ?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYear(), book.getIsbn());
    }

    @Override
    public void updateBook(Book book) {
        jdbcTemplate.update("UPDATE books SET title = ?, author = ?, year = ? WHERE isbn = ?",
                book.getTitle(), book.getAuthor(), book.getYear(), book.getIsbn());
    }

    @Override
    public void deleteBook(String isbn) {
        jdbcTemplate.update("DELETE FROM books WHERE isbn = ?", isbn);
    }

}
