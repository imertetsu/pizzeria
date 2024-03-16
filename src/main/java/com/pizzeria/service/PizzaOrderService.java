package com.pizzeria.service;

import com.pizzeria.persistence.entity.PizzaOrderEntity;
import com.pizzeria.persistence.projection.OrderSummary;
import com.pizzeria.persistence.repository.PizzaOrderRepository;
import com.pizzeria.service.dto.RandomOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Secured("ROLE_ADMIN")
    public List<PizzaOrderEntity> getAllOrders(){
        List<PizzaOrderEntity> listOrders = this.pizzaOrderRepository.findAll();
        listOrders.forEach(order -> System.out.println(order.getCustomer().getName()));
        return listOrders;
    }
    @Secured("ROLE_ADMIN")
    public List<PizzaOrderEntity> getOrdersAfter(){
        LocalDateTime today = LocalDateTime.now();
        return this.pizzaOrderRepository.findAllByDateAfter(today);
    }
    @Secured("ROLE_ADMIN")
    public List<PizzaOrderEntity> getOrdersByMethodOutside(){
        List<String> methods = Arrays.asList(DELIVERY, CARRYOUT);
        return this.pizzaOrderRepository.findAllByMethodIn(methods);
    }
    @Secured("ROLE_ADMIN")//esto es para solo autorizar al admin pueda realizar este metodo
    public List<PizzaOrderEntity> getCustomerOrders(String customerId){
        return this.pizzaOrderRepository.findCustomerOrders(customerId);
    }
    public OrderSummary getSummaryOrder(int orderId){
        return this.pizzaOrderRepository.findSummary(orderId);
    }

    @Secured("ROLE_CUSTOMER")
    @Transactional
    public boolean saveRandomOrder(RandomOrderDTO randomOrderDTO){
        return this.pizzaOrderRepository.saveRandomOrder(randomOrderDTO.getIdCustomer(), randomOrderDTO.getMethod());
    }
}
