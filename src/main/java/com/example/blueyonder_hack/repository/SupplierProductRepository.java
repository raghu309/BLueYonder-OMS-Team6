package com.example.blueyonder_hack.repository;

import com.example.blueyonder_hack.entity.SupplierProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierProductRepository extends JpaRepository<SupplierProduct, Long> {

    // Correct method to find products by supplierId
    List<SupplierProduct> findBySupplier_supplierId(Long supplierId);

    List<SupplierProduct> findByProduct_productId(Long productId);

//    List<SupplierProduct> findBySupplier_supplierId(Long supplierId);
}
