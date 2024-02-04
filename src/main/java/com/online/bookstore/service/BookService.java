package com.online.bookstore.service;

import com.online.bookstore.model.Book;
import com.online.bookstore.model.BorrowRequest;
import com.online.bookstore.model.Category;
import com.online.bookstore.model.User;
import com.online.bookstore.repository.BookRepository;
import com.online.bookstore.repository.BorrowRequestRepository;
import com.online.bookstore.repository.UserRepository;
import com.online.util.exceptions.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BorrowRequestRepository borrowRequestRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }
    public void borrowBook(Long bookId, Long userId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new CustomException("Book not found with ID: " + bookId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("User not found with ID: " + userId));

        if (!book.isAvailability()) {
            throw new CustomException("Book with ID " + bookId + " is not available for borrowing.");
        }

        BorrowRequest borrowRequest = new BorrowRequest();
        borrowRequest.setBook(book);
        borrowRequest.setCustomer(user);
        borrowRequest.setBorrowDate(LocalDate.now());

        borrowRequest.setReturnDate(borrowRequest.getBorrowDate().plusDays(14));
        borrowRequestRepository.save(borrowRequest);

        book.setAvailability(false);
        bookRepository.save(book);

}
}
