package com.seaweed.seaweed.Services;

import com.seaweed.seaweed.Exceptions.findOrdersByIdNotFoundException;
import com.seaweed.seaweed.Models.Login;
import com.seaweed.seaweed.Models.Orders;
import com.seaweed.seaweed.Repositories.OrdersRepo;
import com.seaweed.seaweed.Repositories.ProductsRepos;
import com.seaweed.seaweed.Repositories.UsersRepos;
import com.seaweed.seaweed.dto.customReports.AdminReportCard;
import com.seaweed.seaweed.dto.customReports.CustomersReportCards;
import com.seaweed.seaweed.dto.customReports.FarmerDashboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrdersRepo ordersRepo;

    @Autowired
    private LoginService loginService;

    @Autowired
    private UsersRepos usersRepos;
    @Autowired
    private ProductsRepos productsRepos;

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

    public Orders findByReferenceNumber(String controlNumber){
        return ordersRepo.findByPaymentReference(controlNumber).orElseThrow(()-> new findOrdersByIdNotFoundException("The Resource with "+ controlNumber +"Is not found"));
    }

  public Long getTotal(){
      return ordersRepo.count();
  }

    public List<Orders> findByProductsInsertedByAndOrderStatus(Login l, String orderStatus) {
      return ordersRepo.findByProductsInsertedByAndOrderStatus(l,orderStatus);
    }

    public List<Orders> findByOrderedStatusAndOrderedBy(String orderStatus, Login l) {
      return ordersRepo.findByOrderStatusAndOrderedBy(orderStatus,l);

    }

    public CustomersReportCards customerdashboard(Long id) {
        Integer totalAmount = ordersRepo.reportBoughtPrice(id, "Bought");
        Integer totalBoughtProducts = ordersRepo.reportBought(id, "Bought");
        Integer ordered = ordersRepo.reportBought(id, "Ordered");

        CustomersReportCards c = new CustomersReportCards();
        c.boughtProducts = (totalBoughtProducts != null) ? totalBoughtProducts : 0;
        c.purchasesAmount = (totalAmount != null) ? totalAmount : 0;
        c.orderedProducts = (ordered != null) ? ordered : 0;

        return c;
    }


    public FarmerDashboard farmerDashboard(Long id) {
        Login l = loginService.getById(id);
        Integer ownedProducts = productsRepos.countProductsByInsertedBy(l);
        Integer soldProducts = ordersRepo.reportFarmerSoldProducts(id, "Bought");
        Integer income = ordersRepo.reportFarmerIncome(id, "Bought");

        FarmerDashboard f = new FarmerDashboard();
        f.income = (income != null) ? income : 0;
        f.soldProducts = (soldProducts != null) ? soldProducts : 0;
        f.ownedProducts = (ownedProducts != null) ? ownedProducts : 0;

        return f;
    }



    public AdminReportCard adminDashboard(Long id) {
        Login l = loginService.getById(id);
        Integer ownedProducts = productsRepos.countProductsByInsertedBy(l);
        Integer soldProducts = ordersRepo.reportFarmerSoldProducts(id, "Bought");
        Integer income = ordersRepo.reportFarmerIncome(id, "Bought");
        long products = productsRepos.countProducts();
        long sys = usersRepos.countUsers();
        Integer revenue = ordersRepo.reportSum();

        AdminReportCard a = new AdminReportCard();
        a.soldProducts = (soldProducts != null) ? soldProducts : 0;
        a.setGeneratedRevenue(revenue);
        a.ownedProducts = (ownedProducts != null) ? ownedProducts : 0;
        a.income = (income != null) ? income : 0;
        a.setRegisteredProducts(products);
        a.setSystemUsers(sys);

        return a;
    }



}
