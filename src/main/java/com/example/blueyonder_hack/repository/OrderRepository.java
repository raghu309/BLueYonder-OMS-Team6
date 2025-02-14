package com.example.blueyonder_hack.repository;

import com.example.blueyonder_hack.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // You can add custom query methods here if needed, for example:
    // List<Order> findByCustomerId(Long customerId);
}
