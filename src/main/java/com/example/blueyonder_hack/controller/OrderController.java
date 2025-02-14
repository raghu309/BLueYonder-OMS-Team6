package com.example.blueyonder_hack.controller;

import com.example.blueyonder_hack.entity.Order;
import com.example.blueyonder_hack.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // API endpoint to create an order
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@RequestParam Long customerId, @RequestParam Long productId,
                             @RequestParam Integer quantity, @RequestParam Long supplierId) {
        return orderService.createOrder(customerId, productId, quantity, supplierId);
    }

    // API endpoint to get an order by ID
    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }

    // API endpoint to get all orders
    @GetMapping
    public List<Order> getAllOrders() {
        return (List<Order>) orderService.getAllOrders();
    }
}