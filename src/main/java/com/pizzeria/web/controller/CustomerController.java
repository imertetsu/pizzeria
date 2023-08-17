package com.pizzeria.web.controller;

import com.pizzeria.persistence.entity.CustomerEntity;
import com.pizzeria.service.CustomerService;
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

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }
    @GetMapping()
    public ResponseEntity<List<CustomerEntity>> getAllCustomers(){
        return ResponseEntity.ok(this.customerService.getAllCustomers());
    }
    @GetMapping("/phone/{phone}")
    public ResponseEntity<CustomerEntity> getCustomerByPhone(@PathVariable("phone") String phone){
        return ResponseEntity.ok(this.customerService.getCustomerByPhone(phone));
    }
}
