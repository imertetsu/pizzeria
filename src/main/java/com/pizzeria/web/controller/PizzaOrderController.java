package com.pizzeria.web.controller;

import com.pizzeria.persistence.entity.OrderItemEntity;
import com.pizzeria.persistence.entity.PizzaOrderEntity;
import com.pizzeria.service.PizzaOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pizzaOrders")
public class PizzaOrderController {
    private final PizzaOrderService pizzaOrderService;
    @Autowired
    public PizzaOrderController(PizzaOrderService pizzaOrderService){
        this.pizzaOrderService = pizzaOrderService;
    }

    @GetMapping()
    public ResponseEntity<List<PizzaOrderEntity>> getOrders(){
        return ResponseEntity.ok(this.pizzaOrderService.getAllOrders());
    }
}
