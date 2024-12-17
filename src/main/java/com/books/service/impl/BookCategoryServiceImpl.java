package com.books.service.impl;

import com.books.model.BookCategory;
import com.books.repository.BookCategoryRepository;
import com.books.service.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookCategoryServiceImpl implements BookCategoryService {

    private final BookCategoryRepository bookCategoryRepository;

    @Autowired
    public BookCategoryServiceImpl(BookCategoryRepository bookCategoryRepository) {
        this.bookCategoryRepository = bookCategoryRepository;
    }

    @Override
    public List<BookCategory> getAllCategories() {
        return bookCategoryRepository.findAll();
    }

    @Override
    public BookCategory getCategoryById(Long id) {
        return bookCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public BookCategory createCategory(BookCategory category) {
        return bookCategoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        bookCategoryRepository.deleteById(id);
    }
}

