package com.skypro.library.service;

import com.skypro.library.dao.BookDAO;
import com.skypro.library.dto.BookDTO;
import com.skypro.library.model.Book;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.skypro.library.exception.WrongBookException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{

    private BookDAO bookDAO;
    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public List<BookDTO> getBooks() {
        return bookDAO.getBooks().stream().map(book -> {
            BookDTO bookDto = new BookDTO();
            BeanUtils.copyProperties(book, bookDto);
            return bookDto;
        }).collect(Collectors.toList());

//    public List<Book> getBooks() {
//        return bookDAO.getBooks();
   }

    @Override
    public Book getBookByIsbn(String isbn) {
        if ( bookDAO.getBookByIsbn(isbn) == null)
            throw new WrongBookException("isbn отсутствует - " + isbn);
        return bookDAO.getBookByIsbn(isbn);
    }

    @Override
    public void addBook(Book book) {
        if (!validateBook(book)) throw new WrongBookException("ошибка isbn ");
        bookDAO.addBook(book);
    }

    @Override
    public void updateBook(Book book) {
        if (!validateBook(book)) throw new WrongBookException("ошибка isbn ");
        if( bookDAO.getBookByIsbn(book.getIsbn()) == null) throw new
                WrongBookException("Книга отсутствует");
        bookDAO.updateBook(book);
    }

    @Override
     public void deleteBook(String isbn) {

        if ( bookDAO.getBookByIsbn(isbn) == null)
            throw new WrongBookException("isbn отсутствует - " + isbn);
        bookDAO.deleteBook(isbn);
    }

    private boolean validateBook(Book book) {

        if (book.getTitle() == null || book.getAuthor() == null || book.getIsbn() == null || book.getYear() < 0)
            throw new WrongBookException(" не все поля заполнены ");

        var isbn = book.getIsbn().replaceAll("-", "");;
        if (isbn.length() != 13 || !isbn.matches("[0-9]+"))
                throw new WrongBookException("Invalid ISBN");

        var sum = 0;
        for (var i = 0; i < isbn.length(); i++) {
            int digit = Character.getNumericValue(isbn.charAt(i));
            sum += (i % 2 == 0) ? digit : digit * 3;
        }
        var checkSum = 10 - (sum % 10);
        return checkSum == Character.getNumericValue(isbn.charAt(isbn.length()-1));
    }

}

