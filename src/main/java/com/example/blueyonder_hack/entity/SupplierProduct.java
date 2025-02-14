package com.example.blueyonder_hack.entity;

import com.example.blueyonder_hack.entity.Product;
import com.example.blueyonder_hack.entity.Supplier;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "supplier_product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;  // Supplier of the product

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;  // Product supplied by the supplier

    private Double price;
    private Integer stock;
    private Integer deliveryTime;

}
