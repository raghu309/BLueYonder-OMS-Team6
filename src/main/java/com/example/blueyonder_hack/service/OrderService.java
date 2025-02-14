package com.example.blueyonder_hack.service;

import com.example.blueyonder_hack.entity.Order;
import com.example.blueyonder_hack.entity.Product;
import com.example.blueyonder_hack.entity.Supplier;
import com.example.blueyonder_hack.repository.OrderRepository;
import com.example.blueyonder_hack.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierSelectionService supplierSelectionService;

    // Method to create an order and calculate total price
    public Order createOrder(Long customerId, Long productId, Integer quantity, Long supplierId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();

            // Calculate total price for the order
            Double totalPrice = product.getPrice() * quantity;

            Order order = new Order();
            order.setCustomerId(customerId);
            order.setProduct(product);
            order.setQuantity(quantity);
            order.setStatus("Pending");  // Initial status
            order.setSupplierId(supplierId);
            order.setTotalPrice(totalPrice);

            return orderRepository.save(order);
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    // Method to get an order by its ID
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    // Method to get all orders
    public Iterable<Order> getAllOrders() {
        return orderRepository.findAll();
    }


}