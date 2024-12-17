package com.books.service;

import com.books.model.Order;

public interface OrderService {
    Order createOrder(Order order);
    Order getOrderById(Long id);
}
