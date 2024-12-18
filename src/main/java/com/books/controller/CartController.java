package com.books.controller;

import com.books.dto.CartDTO;
import com.books.service.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/{cartId}/books/{bookId}")
    public void addBookToCart(@PathVariable Long cartId, @PathVariable Long bookId) {
        cartService.addBookToCart(cartId, bookId);
    }

    @DeleteMapping("/{cartId}/books/{bookId}")
    public void removeBookFromCart(@PathVariable Long cartId, @PathVariable Long bookId) {
        cartService.removeBookFromCart(cartId, bookId);
    }

    @GetMapping("/{cartId}")
    public CartDTO getCartDetails(@PathVariable Long cartId) {
        return cartService.getCartDetails(cartId);
    }

}
