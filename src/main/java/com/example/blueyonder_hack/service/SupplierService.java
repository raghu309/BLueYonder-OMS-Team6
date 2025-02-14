package com.example.blueyonder_hack.service;

import com.example.blueyonder_hack.dto.SupplierProductListDTO;
import com.example.blueyonder_hack.dto.SupplierWithProductsDTO;
import com.example.blueyonder_hack.entity.Product;
import com.example.blueyonder_hack.entity.Supplier;
import com.example.blueyonder_hack.entity.SupplierProduct;
import com.example.blueyonder_hack.repository.ProductRepository;
import com.example.blueyonder_hack.repository.SupplierProductRepository;
import com.example.blueyonder_hack.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierProductRepository supplierProductRepository;

    public Supplier registerSupplier(Supplier supplier, List<Product> products) {
        // Ensure supplier name, location, and rating are not null
        if (supplier.getName() == null || supplier.getLocation() == null || supplier.getRating() == null) {
            throw new IllegalArgumentException("Supplier name, location, and rating cannot be null");
        }

        // Save the supplier first
        Supplier savedSupplier = supplierRepository.save(supplier);

        // Save each product and create the SupplierProduct relationship
        for (Product product : products) {
            // Ensure productName, price, stock, and deliveryTime are not null
            if (product.getProductName() == null || product.getPrice() == null ||
                    product.getStock() == null || product.getDeliveryTime() == null) {
                throw new IllegalArgumentException("Product name, price, stock, and deliveryTime cannot be null");
            }

            // Save the product
            Product savedProduct = productRepository.save(product);

            // Create the SupplierProduct relationship
            SupplierProduct supplierProduct = new SupplierProduct();
            supplierProduct.setSupplier(savedSupplier);
            supplierProduct.setProduct(savedProduct);
            supplierProduct.setPrice(product.getPrice());
            supplierProduct.setStock(product.getStock());
            supplierProduct.setDeliveryTime(product.getDeliveryTime());

            // Save the SupplierProduct relationship
            supplierProductRepository.save(supplierProduct);
        }

        return savedSupplier;
    }

    public SupplierWithProductsDTO getSupplierWithProducts(Long supplierId) {
        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        // Fetch associated products for the supplier
        List<SupplierWithProductsDTO.ProductDTO> productDTOList = supplierProductRepository.findBySupplier_supplierId(supplierId)
                .stream()
                .map(supplierProduct -> {
                    SupplierWithProductsDTO.ProductDTO productDTO = new SupplierWithProductsDTO.ProductDTO();
                    productDTO.setProductId(supplierProduct.getProduct().getProductId());
                    productDTO.setPrice(supplierProduct.getPrice());
                    productDTO.setStock(supplierProduct.getStock());
                    productDTO.setDeliveryTime(supplierProduct.getDeliveryTime());
                    return productDTO;
                })
                .collect(Collectors.toList());

        // Map Supplier data to DTO
        SupplierWithProductsDTO supplierWithProductsDTO = new SupplierWithProductsDTO();
        supplierWithProductsDTO.setSupplierId(supplier.getSupplierId());
        supplierWithProductsDTO.setName(supplier.getName());
        supplierWithProductsDTO.setLocation(supplier.getLocation());
        supplierWithProductsDTO.setRating(supplier.getRating());
        supplierWithProductsDTO.setProducts(productDTOList);

        return supplierWithProductsDTO;
    }


    public List<SupplierProductListDTO> getSupplierProductList(Long supplierId) {
        // Retrieve all SupplierProduct records for the given supplierId
        List<SupplierProduct> supplierProducts = supplierProductRepository.findBySupplier_supplierId(supplierId);

        // Map SupplierProduct to SupplierProductListDTO
        return supplierProducts.stream()
                .map(supplierProduct -> {
                    SupplierProductListDTO productDTO = new SupplierProductListDTO();
                    productDTO.setProductId(supplierProduct.getProduct().getProductId());
                    productDTO.setProductName(supplierProduct.getProduct().getProductName());
                    productDTO.setPrice(supplierProduct.getPrice());
                    productDTO.setStock(supplierProduct.getStock());
                    return productDTO;
                })
                .collect(Collectors.toList());
    }


}
