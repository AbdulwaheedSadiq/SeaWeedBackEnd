package com.seaweed.seaweed.Repositories;

import com.seaweed.seaweed.Models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdersRepo extends JpaRepository<Orders,Long> {
    Optional<Orders> findOrdersById(Long id);
}
