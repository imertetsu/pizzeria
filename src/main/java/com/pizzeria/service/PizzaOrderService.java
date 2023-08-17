package com.pizzeria.service;

import com.pizzeria.persistence.entity.PizzaOrderEntity;
import com.pizzeria.persistence.repository.PizzaOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class PizzaOrderService {
    private final PizzaOrderRepository pizzaOrderRepository;

    private final String DELIVERY = "D";
    private final String CARRYOUT = "C";
    private final String ON_SITE = "S";

    @Autowired
    public PizzaOrderService(PizzaOrderRepository pizzaOrderRepository){
        this.pizzaOrderRepository = pizzaOrderRepository;
    }

    public List<PizzaOrderEntity> getAllOrders(){
        List<PizzaOrderEntity> listOrders = this.pizzaOrderRepository.findAll();
        listOrders.forEach(order -> System.out.println(order.getCustomer().getName()));
        return listOrders;
    }

    public List<PizzaOrderEntity> getOrdersAfter(){
        LocalDateTime today = LocalDateTime.now();
        return this.pizzaOrderRepository.findAllByDateAfter(today);
    }
    public List<PizzaOrderEntity> getOrdersByMethodOutside(){
        List<String> methods = Arrays.asList(DELIVERY, CARRYOUT);
        return this.pizzaOrderRepository.findAllByMethodIn(methods);
    }
}
