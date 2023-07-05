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

//    @Query(value = "select * from sw_orders where customer =?1 AND password =?2 LIMIT 1;",nativeQuery = true)
//    Optional<Login> findUser(String u, String p);
}
