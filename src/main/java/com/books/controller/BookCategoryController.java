package com.books.controller;

import com.books.model.BookCategory;
import com.books.service.BookCategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book-categories")
public class BookCategoryController {

    private final BookCategoryService bookCategoryService;

    public BookCategoryController(BookCategoryService bookCategoryService) {
        this.bookCategoryService = bookCategoryService;
    }

    @GetMapping
    public List<BookCategory> getAllCategories() {
        return bookCategoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public BookCategory getCategoryById(@PathVariable Long id) {
        return bookCategoryService.getCategoryById(id);
    }

    @PostMapping
    public BookCategory createCategory(@RequestBody BookCategory category) {
        return bookCategoryService.createCategory(category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        bookCategoryService.deleteCategory(id);
    }
}
