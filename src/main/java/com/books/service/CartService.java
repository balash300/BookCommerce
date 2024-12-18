package com.books.service;

import com.books.dto.CartDTO;

public interface CartService {
    void addBookToCart(Long cartId, Long bookId);
    void removeBookFromCart(Long cartId, Long bookId);
    CartDTO getCartDetails(Long cartId);
}
