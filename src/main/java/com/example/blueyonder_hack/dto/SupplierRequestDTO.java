package com.example.blueyonder_hack.dto;

import com.example.blueyonder_hack.entity.Product;
import lombok.Data;

import java.util.List;

@Data
public class SupplierRequestDTO {
    private String name;
    private String location;
    private Double rating;
    private List<Product> products;
}
