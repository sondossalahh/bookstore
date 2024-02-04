package com.online.bookstore.repository;

import com.online.bookstore.model.Book;
import com.online.bookstore.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT b FROM Book b WHERE b.category.id = :categoryId")
    List<Book> findBooksByCategoryId(Long categoryId);
}
