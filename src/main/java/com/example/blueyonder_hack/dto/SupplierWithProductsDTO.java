package com.example.blueyonder_hack.dto;

import com.example.blueyonder_hack.entity.Product;
import lombok.Data;

import java.util.List;

@Data
public class SupplierWithProductsDTO {
    private Long supplierId;
    private String name;
    private String location;
    private Double rating;
    private List<ProductDTO> products; // List of products associated with the supplier

    // Nested DTO for Product
    @Data
    public static class ProductDTO {
        private Long productId;
        private Double price;
        private Integer stock;
        private Integer deliveryTime;
    }
}
