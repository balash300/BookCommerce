package com.books.service.impl;

import com.books.dto.CartDTO;
import com.books.dto.CartItemDTO;
import com.books.model.Book;
import com.books.model.Cart;
import com.books.model.CartItem;
import com.books.repository.BookRepository;
import com.books.repository.CartItemRepository;
import com.books.repository.CartRepository;
import com.books.service.CartService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final BookRepository bookRepository;

    public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository, BookRepository bookRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public void addBookToCart(Long cartId, Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (!book.getIsActive()) {
            throw new RuntimeException("Book is not active and cannot be added to the cart");
        }

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        CartItem existingCartItem = cartItemRepository.findByCartAndBook(cart, book).orElse(null);
        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + 1);
            cartItemRepository.save(existingCartItem);
        } else {
            CartItem cartItem = CartItem.builder()
                    .book(book)
                    .cart(cart)
                    .quantity(1)
                    .build();

            cartItemRepository.save(cartItem);
        }
    }

    @Override
    @Transactional
    public void removeBookFromCart(Long cartId, Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        CartItem cartItem = cartItemRepository.findByCartAndBook(cart, book)
                .orElseThrow(() -> new RuntimeException("CartItem not found"));

        cartItemRepository.delete(cartItem);
    }

    @Override
    @Transactional
    public CartDTO getCartDetails(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        List<CartItemDTO> items = cartItemRepository.findByCart(cart).stream()
                .map(cartItem -> CartItemDTO.builder()
                        .bookId(cartItem.getBook().getId())
                        .bookTitle(cartItem.getBook().getTitle())
                        .quantity(cartItem.getQuantity())
                        .price(cartItem.getBook().getPrice())
                        .build())
                .collect(Collectors.toList());

        return CartDTO.builder()
                .id(cart.getId())
                .items(items)
                .totalPrice(cart.getCartItems().stream()
                        .mapToDouble(item -> item.getQuantity() * item.getBook().getPrice())
                        .sum())
                .build();
    }
}