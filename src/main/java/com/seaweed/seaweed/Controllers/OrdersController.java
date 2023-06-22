package com.seaweed.seaweed.Controllers;

import com.seaweed.seaweed.Models.Orders;
import com.seaweed.seaweed.Services.OrderService;
import com.seaweed.seaweed.dto.orders.OrdersRequest;
import com.seaweed.seaweed.dto.orders.OrdersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin
@RestController
@RequestMapping("orders/")
public class OrdersController {
    @Autowired
    OrderService orderService;

    @PostMapping("create")
    public ResponseEntity<OrdersResponse> create(@RequestBody OrdersRequest request){

            Orders o = new Orders();
            o.setProductId(request.getProductId());
            o.setCustomerId(request.getCustomerId());
            //o.setCreatedDate(LocalDate.now());
            o.setQuantity(request.getQuantity());
            o.setPrice(request.getPrice());
            o.setPaymentReference(request.getPaymentReference());
            o.setPaymentStatus(request.getPaymentStatus());
            o.setOrderStatus(request.getOrderStatus());
            orderService.insert(o);

//response
            OrdersResponse response = new OrdersResponse();
            response.productId =o.productId;
            response.createdDate = o.createdDate;
            response.customerId = o.customerId;
            response.price = o.price;
            response.quantity = o.quantity;
            response.paymentReference = o.paymentReference;
            response.paymentStatus = o.paymentStatus;
            response.orderStatus = o.orderStatus;

        return  ResponseEntity.ok(response);
    }


    @GetMapping("getAll")
    public ResponseEntity<List<Orders>> getAll(){
        List<Orders> orders = orderService.getAll();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<Orders> getById(@PathVariable Long id){
        Orders orders = orderService.findById(id);
        return ResponseEntity.ok(orders);
    }


    @PutMapping("update/{id}")
    public ResponseEntity<OrdersResponse> update(@RequestBody OrdersRequest request,@PathVariable Long id){

        Orders o = orderService.findById(id);

        //Orders o = new Orders();
        o.setProductId(request.productId);
        o.setCustomerId(request.customerId);
        //o.setCreatedDate(LocalDate.now());
        o.setQuantity(request.getQuantity());
        o.setPrice(request.getPrice());
        o.setPaymentReference(request.getPaymentReference());
        o.setPaymentStatus(request.getPaymentStatus());
        o.setOrderStatus(request.getOrderStatus());

        orderService.insert(o);

//response
        OrdersResponse response = new OrdersResponse();
        response.productId =o.productId;
        response.createdDate = o.createdDate;
        response.customerId = o.customerId;
        response.price = o.price;
        response.quantity = o.quantity;
        response.paymentReference = o.paymentReference;
        response.paymentStatus = o.paymentStatus;
        response.orderStatus = o.orderStatus;

        return  ResponseEntity.ok(response);
    }
}
