package com.example.blueyonder_hack.service;

import com.example.blueyonder_hack.entity.SupplierProduct;
import com.example.blueyonder_hack.entity.Supplier;
import com.example.blueyonder_hack.repository.SupplierProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierSelectionService {

    @Autowired
    private SupplierProductRepository supplierProductRepository;

    // Method to select the best supplier for a product order
    public Supplier selectBestSupplier(Long productId, Integer quantity) {
        // Retrieve all available suppliers for the product
        List<SupplierProduct> supplierProducts = supplierProductRepository.findByProduct_productId(productId);

        // Filter suppliers based on stock availability and price
        return supplierProducts.stream()
                .filter(sp -> sp.getStock() >= quantity) // Ensure the supplier has enough stock
                .min((sp1, sp2) -> {
                    // Compare by price first, then delivery time, then rating
                    int priceComparison = sp1.getPrice().compareTo(sp2.getPrice());
                    if (priceComparison != 0) return priceComparison;
                    int deliveryTimeComparison = sp1.getDeliveryTime().compareTo(sp2.getDeliveryTime());
                    if (deliveryTimeComparison != 0) return deliveryTimeComparison;
                    return sp1.getSupplier().getRating().compareTo(sp2.getSupplier().getRating());
                })
                .map(SupplierProduct::getSupplier) // Get the supplier of the best product
                .orElseThrow(() -> new RuntimeException("No supplier found for the product with sufficient stock."));
    }
}
