package com.seaweed.seaweed.Services;

import com.seaweed.seaweed.Exceptions.findOrdersByIdNotFoundException;
import com.seaweed.seaweed.Models.Login;
import com.seaweed.seaweed.Models.Orders;
import com.seaweed.seaweed.Repositories.OrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrdersRepo ordersRepo;

  public Orders insert(Orders orders){
      return ordersRepo.save(orders);
  }

  public List<Orders> getAll(){
      return ordersRepo.findAll();
  }

  public Optional<Orders> getById(Long id){
      return ordersRepo.findById(id);
  }

    public Orders findById(Long id){
        return ordersRepo.findOrdersById(id).orElseThrow(()-> new findOrdersByIdNotFoundException("The Resource with "+ id +"Is not found"));
    }

  public Long getTotal(){
      return ordersRepo.count();
  }

    public List<Orders> findByProductsInsertedByAndOrderStatus(Login l, String orderStatus) {
      return ordersRepo.findByProductsInsertedByAndOrderStatus(l,orderStatus);
    }
}
