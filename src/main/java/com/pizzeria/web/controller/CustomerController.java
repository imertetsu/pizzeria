package com.pizzeria.web.controller;

import com.pizzeria.persistence.entity.CustomerEntity;
import com.pizzeria.persistence.entity.PizzaOrderEntity;
import com.pizzeria.service.CustomerService;
import com.pizzeria.service.PizzaOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final PizzaOrderService pizzaOrderService;

    @Autowired
    public CustomerController(
            CustomerService customerService,
            PizzaOrderService pizzaOrderService){
        this.customerService = customerService;
        this.pizzaOrderService = pizzaOrderService;
    }
    @GetMapping()
    public ResponseEntity<List<CustomerEntity>> getAllCustomers(){
        return ResponseEntity.ok(this.customerService.getAllCustomers());
    }
    @GetMapping("/phone/{phone}")
    public ResponseEntity<CustomerEntity> getCustomerByPhone(@PathVariable("phone") String phone){
        return ResponseEntity.ok(this.customerService.getCustomerByPhone(phone));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<PizzaOrderEntity>> getCustomerOrders(@PathVariable("customerId") String customerId){
        return ResponseEntity.ok(this.pizzaOrderService.getCustomerOrders(customerId));
    }
}
