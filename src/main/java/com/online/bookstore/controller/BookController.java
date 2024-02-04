package com.online.bookstore.controller;

import com.online.bookstore.model.Book;
import com.online.bookstore.service.BookService;
import com.online.bookstore.service.CategoryService;
import com.online.util.exceptions.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService=bookService;
    }
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book savedBook = bookService.saveBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }
    @PostMapping("/{id}/update")
    public Book updateBookDetails(@PathVariable("id") long bookId, @RequestBody Book updatedBook) throws CustomException {
        Book existingBook = bookService.getBookById(bookId);
        if (existingBook != null) {

            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setCategory(updatedBook.getCategory());
            existingBook.setAvailability(updatedBook.isAvailability());

            return bookService.saveBook(existingBook);
        } else {
            throw new CustomException("Book not found with ID: " + bookId);
        }
    }
@PostMapping("/{id}/setAvailability")
    public Book setBookAvailability(@PathVariable("id") long bookId, @RequestBody boolean availability) {
        Book existingBook = bookService.getBookById(bookId);
        if (existingBook != null) {
            existingBook.setAvailability(availability);
            return bookService.saveBook(existingBook);
        } else {
            throw new CustomException("Book not found with ID: " + bookId);
        }
    }
    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBookDetails(@PathVariable Long bookId) {
        try {
            Book bookDetails = bookService.getBookById(bookId);
            return new ResponseEntity<>(bookDetails, HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/{bookId}/borrow")
    public ResponseEntity<String> borrowBook(@RequestParam Long userId, @RequestParam Long bookId) {
        try {
            bookService.borrowBook(bookId, userId);
            return new ResponseEntity<>("Borrow request successful.", HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
