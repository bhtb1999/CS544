package com.lab8.Lab8PartA.controller;

import com.lab8.Lab8PartA.domain.Book;
import com.lab8.Lab8PartA.domain.Books;
import com.lab8.Lab8PartA.domain.CustomError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BookController {
    private Map<String, Book> books = new HashMap<>();

    public BookController() {
        books.put("1", new Book("Frank", "Brown", "1", 1000));
        books.put("2", new Book("Mary", "Jones", "2", 2000));
    }

    @PostMapping("/books")
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        if (books.containsKey(book.getIsbn())) {
            return new ResponseEntity<CustomError>(new CustomError("Book already exists"), HttpStatus.CONFLICT);
        }
        books.put(book.getIsbn(), book);
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @PutMapping("/books/{isbn}")
    public ResponseEntity<?> updateBook(@PathVariable String isbn, @RequestBody Book book) {
        if (!books.containsKey(isbn)) {
            return new ResponseEntity<CustomError>(new CustomError("Book not found"), HttpStatus.NOT_FOUND);
        }
        books.put(isbn, book);
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @DeleteMapping("/books/{isbn}")
    public ResponseEntity<?> deleteBook(@PathVariable String isbn) {
        if (!books.containsKey(isbn)) {
            return new ResponseEntity<CustomError>(new CustomError("Book not found"), HttpStatus.NOT_FOUND);
        }
        books.remove(isbn);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("books/{isbn}")
    public ResponseEntity<?> getBook(@PathVariable String isbn) {
        if (!books.containsKey(isbn)) {
            return new ResponseEntity<>(new CustomError("Book not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(books.get(isbn), HttpStatus.OK);
    }

    @GetMapping("/books")
    public ResponseEntity<?> getBooks() {
        return new ResponseEntity<Books>(new Books(books.values()), HttpStatus.OK);
    }

    @GetMapping("/books/search/{authorName}")
    public ResponseEntity<?> searchBooks(@PathVariable String authorName) {
        Books allBooks = new Books();
        for (Book book : books.values()) {
            if (book.getAuthor().equals(authorName)) {
                allBooks.addBook(book);
            }
        }
        return new ResponseEntity<Books>(allBooks, HttpStatus.OK);

    }

}
