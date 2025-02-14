package com.example.blueyonder_hack.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private Integer stock;
    private Integer deliveryTime; // Delivery time in days

    @OneToMany(mappedBy = "product")
    private List<SupplierProduct> supplierProduct;
}