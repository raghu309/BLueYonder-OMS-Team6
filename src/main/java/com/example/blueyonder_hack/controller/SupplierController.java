package com.example.blueyonder_hack.controller;

import com.example.blueyonder_hack.dto.SupplierDTO;
import com.example.blueyonder_hack.dto.SupplierProductListDTO;
import com.example.blueyonder_hack.dto.SupplierRequestDTO;
import com.example.blueyonder_hack.dto.SupplierWithProductsDTO;
import com.example.blueyonder_hack.entity.Supplier;
import com.example.blueyonder_hack.entity.SupplierProduct;
import com.example.blueyonder_hack.service.SupplierProductService;
import com.example.blueyonder_hack.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private SupplierProductService supplierProductService;

  // API endpoint to register a new supplier and their products
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Supplier registerSupplier(@RequestBody SupplierRequestDTO supplierRequestDTO) {
        // Convert DTO to Supplier and List<Product>
        Supplier supplier = new Supplier();
        supplier.setName(supplierRequestDTO.getName());
        supplier.setLocation(supplierRequestDTO.getLocation());
        supplier.setRating(supplierRequestDTO.getRating());

        // Save the supplier and the products
        return supplierService.registerSupplier(supplier, supplierRequestDTO.getProducts());
    }

    @GetMapping("/{supplierId}")
    public SupplierWithProductsDTO getSupplierWithProducts(@PathVariable Long supplierId) {
        return supplierService.getSupplierWithProducts(supplierId);
    }

    @GetMapping("/{productId}/suppliers")
    public List<SupplierDTO> getSuppliersForProduct(@PathVariable Long productId) {
        return supplierProductService.getSuppliersForProduct(productId);
    }

    @GetMapping("/{supplierId}/products")
    public List<SupplierProductListDTO> getSupplierProductList(@PathVariable Long supplierId) {
        return supplierService.getSupplierProductList(supplierId);
    }

}


