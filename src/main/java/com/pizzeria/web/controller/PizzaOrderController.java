package com.pizzeria.web.controller;

import com.pizzeria.persistence.entity.OrderItemEntity;
import com.pizzeria.persistence.entity.PizzaOrderEntity;
import com.pizzeria.persistence.projection.OrderSummary;
import com.pizzeria.service.PizzaOrderService;
import com.pizzeria.service.dto.RandomOrderDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Pizza Order Controller", description = "In this section we are going to create, read, update and delete Pizza Orders")
@RestController
@RequestMapping("/pizzaOrders")
public class PizzaOrderController {
    private final PizzaOrderService pizzaOrderService;
    @Autowired
    public PizzaOrderController(PizzaOrderService pizzaOrderService){
        this.pizzaOrderService = pizzaOrderService;
    }

    @Operation(summary = "Get all pizza orders. Role ADMIN Required", description = "We are going to retrieve a list of all pizza orders")
    @GetMapping()
    public ResponseEntity<List<PizzaOrderEntity>> getOrders(){
        return ResponseEntity.ok(this.pizzaOrderService.getAllOrders());
    }
    @Operation(summary = "Get all pizza orders by date. Role ADMIN Required", description = "We are going to retrieve a list of all pizza orders by its date")
    @GetMapping("/date")
    public ResponseEntity<List<PizzaOrderEntity>> getOrdersByDate(){
        return ResponseEntity.ok(this.pizzaOrderService.getOrdersAfter());
    }
    @Operation(summary = "Get all pizza orders outside. Role ADMIN Required", description = "We are going to retrieve a list of all pizza orders outside")
    @GetMapping("/outside")
    public ResponseEntity<List<PizzaOrderEntity>> getOrdersByMethodsOutside(){
        return ResponseEntity.ok(this.pizzaOrderService.getOrdersByMethodOutside());
    }

    @Operation(summary = "Get all pizza orders by a customer. Role ADMIN Required", description = "We are going to retrieve a list of all pizza orders by a customer id")
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<PizzaOrderEntity>> getCustomerOrders(@PathVariable("customerId") String customerId){
        return ResponseEntity.ok(this.pizzaOrderService.getCustomerOrders(customerId));
    }

    @Operation(summary = "Get pizza order by id. Role ADMIN Required", description = "We are going to retrieve a pizza order by its id")
    @GetMapping("/customizedOrder/{orderId}")
    public ResponseEntity<OrderSummary> getSummaryOrder(@PathVariable("orderId") int orderId){
        return ResponseEntity.ok(this.pizzaOrderService.getSummaryOrder(orderId));
    }

    @Operation(summary = "Save a random pizza order. Role ADMIN Required", description = "We are going to save a random pizza order with 20% OFF")
    @PostMapping("/random")
    public ResponseEntity<Boolean> randomPizzaOrder(@RequestBody RandomOrderDTO randomOrderDTO){
        return ResponseEntity.ok(this.pizzaOrderService.saveRandomOrder(randomOrderDTO));
    }
}
