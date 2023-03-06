package com.skypro.library.model;

public class Book {

    private String title;
    private String author;
    private Integer year;
    private String isbn;

    public Book() {}
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}
    public String getAuthor() {return author;}
    public void setAuthor(String author) {this.author = author;}
    public Integer getYear() {return year;}
    public void setYear(int year) {this.year = year;}
    public String getIsbn() {return isbn;}
    public void setIsbn(String isbn) {this.isbn = isbn;}
}
