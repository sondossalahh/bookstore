package com.online.bookstore.controller;

import com.online.bookstore.model.Book;
import com.online.bookstore.model.Category;
import com.online.bookstore.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;
    public CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
    @GetMapping("/{categoryId}/books")
    public List<Book>getBooksByCategory(@PathVariable("categoryId") Long categoryId){
        return categoryService.getBooksByCategory(categoryId);
    }
}
