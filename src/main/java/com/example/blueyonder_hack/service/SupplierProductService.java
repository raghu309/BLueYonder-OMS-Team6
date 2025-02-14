package com.example.blueyonder_hack.service;

import com.example.blueyonder_hack.dto.SupplierDTO;
import com.example.blueyonder_hack.dto.SupplierWithProductsDTO;
import com.example.blueyonder_hack.entity.Product;
import com.example.blueyonder_hack.entity.Supplier;
import com.example.blueyonder_hack.entity.SupplierProduct;
import com.example.blueyonder_hack.repository.SupplierProductRepository;
import com.example.blueyonder_hack.repository.ProductRepository;
import com.example.blueyonder_hack.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierProductService {

    @Autowired
    private SupplierProductRepository supplierProductRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ProductRepository productRepository;

    // Method to create a new SupplierProduct relationship
    public SupplierProduct createSupplierProduct(Long supplierId, Long productId, Double price, Integer stock, Integer deliveryTime) {
        // Fetch the supplier and product by their IDs
        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Create and set the SupplierProduct relationship
        SupplierProduct supplierProduct = new SupplierProduct();
        supplierProduct.setSupplier(supplier);
        supplierProduct.setProduct(product);
        supplierProduct.setPrice(price);
        supplierProduct.setStock(stock);
        supplierProduct.setDeliveryTime(deliveryTime);

        // Save and return the SupplierProduct relationship
        return supplierProductRepository.save(supplierProduct);
    }

    // Method to get all products associated with a supplier
    public List<SupplierProduct> getProductsBySupplier(Long supplierId) {
        return supplierProductRepository.findBySupplier_supplierId(supplierId);
    }

    public List<SupplierDTO> getSuppliersForProduct(Long productId) {
        List<SupplierProduct> supplierProducts = supplierProductRepository.findByProduct_productId(productId);

        // Map SupplierProduct to SupplierDTO (only supplier details)
        return supplierProducts.stream()
                .map(supplierProduct -> {
                    SupplierDTO supplierDTO = new SupplierDTO();
                    supplierDTO.setSupplierId(supplierProduct.getSupplier().getSupplierId());
                    supplierDTO.setName(supplierProduct.getSupplier().getName());
                    supplierDTO.setLocation(supplierProduct.getSupplier().getLocation());
                    return supplierDTO;
                })
                .collect(Collectors.toList());
    }

}
