package com.online.bookstore.service;

import com.online.bookstore.model.Book;
import com.online.bookstore.model.Category;
import com.online.bookstore.repository.BookRepository;
import com.online.bookstore.repository.CategoryRepository;
import com.online.util.exceptions.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    private BookRepository bookRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository,
                           BookRepository bookRepository){
        this.categoryRepository=categoryRepository;
        this.bookRepository = bookRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category updatedCategory) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new CustomException("Category not found with ID: " + id));
        existingCategory.setName(updatedCategory.getName());
        return categoryRepository.save(existingCategory);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
    public List<Book> getBooksByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CustomException("Category not found with ID: " + categoryId));
        List<Book> books= categoryRepository.findBooksByCategoryId(categoryId);
        return books;
    }
}