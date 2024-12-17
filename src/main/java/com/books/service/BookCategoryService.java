package com.books.service;

import com.books.model.BookCategory;

import java.util.List;

public interface BookCategoryService {
    List<BookCategory> getAllCategories();
    BookCategory getCategoryById(Long id);
    BookCategory createCategory(BookCategory category);
    void deleteCategory(Long id);
}
