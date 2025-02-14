package com.example.blueyonder_hack.dto;

import lombok.Data;

@Data
public class SupplierProductListDTO {
    private Long productId;
    private String productName;
    private Double price;
    private Integer stock;
}
