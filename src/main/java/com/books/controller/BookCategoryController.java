package com.books.controller;

import com.books.model.BookCategory;
import com.books.service.BookCategoryService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<BookCategory>> getAllCategories() {
        return ResponseEntity.ok(bookCategoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookCategory> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(bookCategoryService.getCategoryById(id));
    }

    @PostMapping
    public ResponseEntity<BookCategory> createCategory(@RequestBody BookCategory category) {
        return ResponseEntity.ok(bookCategoryService.createCategory(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        bookCategoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
