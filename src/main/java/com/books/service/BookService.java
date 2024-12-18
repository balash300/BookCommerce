package com.books.service;

import com.books.dto.BookDTO;
import com.books.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book createBook(BookDTO bookDTO);
    void deleteBook(Long id);

    void updateIsActiveBook(Long bookId, boolean isActive);
}
