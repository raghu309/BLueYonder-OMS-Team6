package com.example.blueyonder_hack.entity;

import com.example.blueyonder_hack.entity.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Order ID

    private Long customerId;  // Customer ID (just a number)

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;  // The product being ordered

    private Integer quantity;  // The quantity of the product ordered

    private String status;  // Status of the order, e.g., "Pending", "Completed"

    private Long supplierId;  // Supplier ID (just a number)

    private Double totalPrice;  // Total price of the order (calculated from quantity and product price)
}
