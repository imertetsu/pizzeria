package com.pizzeria.persistence.repository;

import com.pizzeria.persistence.entity.PizzaOrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PizzaOrderRepository extends ListCrudRepository<PizzaOrderEntity, Integer> {
    List<PizzaOrderEntity> findAllByDateAfter(LocalDateTime localDateTime);
    List<PizzaOrderEntity> findAllByMethodIn(List<String> methods);
    @Query(value = "select * from pizza_order where id_customer = :customerId", nativeQuery = true)
    List<PizzaOrderEntity> findCustomerOrders(@Param("customerId") String customerId);
}
