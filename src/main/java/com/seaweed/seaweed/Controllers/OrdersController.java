package com.seaweed.seaweed.Controllers;

import com.seaweed.seaweed.Models.Login;
import com.seaweed.seaweed.Models.Orders;
import com.seaweed.seaweed.Models.Products;
import com.seaweed.seaweed.Services.LoginService;
import com.seaweed.seaweed.Services.OrderService;
import com.seaweed.seaweed.Services.ProductService;
import com.seaweed.seaweed.dto.customReports.AdminReportCard;
import com.seaweed.seaweed.dto.customReports.CustomersReportCards;
import com.seaweed.seaweed.dto.customReports.FarmerDashboard;
import com.seaweed.seaweed.dto.orders.OrdersRequest;
import com.seaweed.seaweed.dto.orders.OrdersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@CrossOrigin
@RestController
@RequestMapping("orders/")
public class OrdersController {
    @Autowired
    OrderService orderService;

    @Autowired
    LoginService loginService;

    @Autowired
    ProductService productService;

    @PostMapping("create")
    public ResponseEntity<OrdersResponse> create(@RequestBody OrdersRequest request){

            Orders o = new Orders();
            Login login = new Login();
            login.setId(request.getCustomerId());

            Products products = new Products();
            products.setId(request.getProductId());

            o.setCreatedDate(LocalDateTime.now());
            o.setOrderedBy(login);
            o.setProducts(products);
            o.setQuantity(request.getQuantity());
            o.setPrice(request.getPrice());
            o.setPaymentReference(request.getPaymentReference());
            o.setPaymentStatus("Billing");
            o.setOrderStatus("Ordered");
            orderService.insert(o);

            Products products1 = productService.getById(request.getProductId());
            int remains = products1.getQuantity() - request.getQuantity();
            if(remains <= 0){
                products1.setQuantity(0);
                products1.setDescription("SoldOut");
                products1.setStatus("soldOut");
            }else {
                products1.setQuantity(remains);
            }
            productService.insert(products1);


//response
            OrdersResponse response = new OrdersResponse();
            response.createdDate = o.createdDate;
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

    @PutMapping("UpdatePayments/{controlNumber}")
    public ResponseEntity<Orders> UpdatePayments(@PathVariable String controlNumber){

        Orders orders = orderService.findByReferenceNumber(controlNumber);
        orders.setOrderStatus("Bought");
        orders.setPaymentStatus("Bought");
        orderService.insert(orders);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("getByFarmerId/{id}")
    public ResponseEntity<List<Orders>> getByInsertedId(@PathVariable Long id){
        Login l = loginService.getById(id);
        String orderStatus = "Ordered";


        List<Orders> orders = orderService.findByProductsInsertedByAndOrderStatus(l,orderStatus);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("getSold/{id}")
    public ResponseEntity<List<Orders>> getSold(@PathVariable Long id){
        Login l = loginService.getById(id);
        String orderStatus = "Bought";


        List<Orders> orders = orderService.findByProductsInsertedByAndOrderStatus(l,orderStatus);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("customerDashboard/{id}")
    public ResponseEntity<CustomersReportCards> customerDashboard(@PathVariable Long id){

        CustomersReportCards c = orderService.customerdashboard(id);
        return ResponseEntity.ok(c);
    }

    @GetMapping("farmerDashboard/{id}")
    public ResponseEntity<FarmerDashboard> farmerDashboard(@PathVariable Long id){

        FarmerDashboard f = orderService.farmerDashboard(id);
        return ResponseEntity.ok(f);
    }

    @GetMapping("adminDashboard/{id}")
    public ResponseEntity<AdminReportCard> adminDashboard(@PathVariable Long id){

        AdminReportCard a = orderService.adminDashboard(id);
        return ResponseEntity.ok(a);
    }

    @GetMapping("orderById/{id}")
    public ResponseEntity<List<Orders>> orderById(@PathVariable Long id){
        Login l = loginService.getById(id);
        String orderStatus = "Ordered";


        List<Orders> orders = orderService.findByOrderedStatusAndOrderedBy(orderStatus,l);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("soldById/{id}")
    public ResponseEntity<List<Orders>> soldById(@PathVariable Long id){
        Login l = loginService.getById(id);
        String orderStatus = "Bought";


        List<Orders> orders = orderService.findByOrderedStatusAndOrderedBy(orderStatus,l);
        return ResponseEntity.ok(orders);
    }


    @PutMapping("update/{id}")
    public ResponseEntity<OrdersResponse> update(@RequestBody OrdersRequest request,@PathVariable Long id){

        Orders o = orderService.findById(id);
        o.setQuantity(request.getQuantity());
        o.setPrice(request.getPrice());
        o.setPaymentReference(request.getPaymentReference());
        o.setPaymentStatus(request.getPaymentStatus());
        o.setOrderStatus(request.getOrderStatus());

        orderService.insert(o);

//response
        OrdersResponse response = new OrdersResponse();
        response.createdDate = o.createdDate;
        response.price = o.price;
        response.quantity = o.quantity;
        response.paymentReference = o.paymentReference;
        response.paymentStatus = o.paymentStatus;
        response.orderStatus = o.orderStatus;

        return  ResponseEntity.ok(response);
    }
}
