package com.lab8.Lab8PartA.domain;

import java.util.ArrayList;
import java.util.Collection;

public class Books {
    private Collection<Book> books;

    public Books() {
        books = new ArrayList<>();
    }
    public Books(Collection<Book> books) {
        this.books = books;
    }

    public Collection<Book> getBooks() {
        return books;
    }

    public void setBooks(Collection<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public String toString() {
        return "Books{" +
                "books=" + books +
                '}';
    }
}
