package com.pizzeria.service;

import com.pizzeria.persistence.entity.CustomerEntity;
import com.pizzeria.persistence.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    public List<CustomerEntity> getAllCustomers(){
        return this.customerRepository.findAll();
    }

    public CustomerEntity getCustomerByPhone(String phone){
        return this.customerRepository.findByPhone(phone);
    }

}
