package com.books.service.impl;

import com.books.dto.BookDTO;
import com.books.model.Book;
import com.books.model.BookCategory;
import com.books.repository.BookCategoryRepository;
import com.books.repository.BookRepository;
import com.books.service.BookService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookCategoryRepository bookCategoryRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BookCategoryRepository bookCategoryRepository) {
        this.bookRepository = bookRepository;
        this.bookCategoryRepository = bookCategoryRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @Override
    @Transactional
    public Book createBook(BookDTO bookDTO) {
        BookCategory category = bookCategoryRepository.findByCategoryId(bookDTO.getCategoryId());
        if (category == null) {
            throw new RuntimeException("Category not found");
        }

        Book book = Book.builder()
                .title(bookDTO.getTitle())
                .author(bookDTO.getAuthor())
                .price(bookDTO.getPrice())
                .category(category)
                .imageURL(bookDTO.getImageURL())
                .build();

        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}