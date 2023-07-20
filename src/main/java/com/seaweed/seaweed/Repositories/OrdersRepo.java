package com.seaweed.seaweed.Repositories;

import com.seaweed.seaweed.Models.Login;
import com.seaweed.seaweed.Models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepo extends JpaRepository<Orders,Long> {
    Optional<Orders> findOrdersById(Long id);

    List<Orders> findByProductsInsertedByAndOrderStatus(Login l, String orderStatus);

    List<Orders> findByOrderStatusAndOrderedBy(String orderStatus, Login l);

    Optional<Orders> findByPaymentReference(String controlNumber);


    @Query(value = "SELECT COUNT(order_id) from sw_orders where order_status = ?2 and customer_id = ?1",nativeQuery = true)
    Integer reportBought(Long id,String status);

    @Query(value = "SELECT SUM(price) from sw_orders where order_status = ?2 and customer_id = ?1",nativeQuery = true)
    Integer reportBoughtPrice(Long id,String status);

    @Query(value = "SELECT SUM(price) from sw_orders",nativeQuery = true)
    Integer reportSum();

    @Query(value = "SELECT SUM(o.price) FROM sw_orders o INNER JOIN sw_products p where o.product_id = p.product_id" +
            " AND p.inserted_by = ?1 AND o.order_status = ?2",nativeQuery = true)
    Integer reportFarmerIncome(Long id,String status);

    @Query(value = "SELECT COUNT(order_id) FROM sw_orders o INNER JOIN sw_products p where o.product_id = p.product_id" +
            " AND p.inserted_by = ?1 AND o.order_status = ?2",nativeQuery = true)
    Integer reportFarmerSoldProducts(Long id,String status);

}
