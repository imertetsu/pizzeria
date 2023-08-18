package com.pizzeria.persistence.repository;

import com.pizzeria.persistence.entity.PizzaOrderEntity;
import com.pizzeria.persistence.projection.OrderSummary;
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

    //Query personalizado
    @Query(value =
            "SELECT po.id_order AS idOrder, cu.name AS customerName, po.date AS orderDate, po.total AS orderTotal, GROUP_CONCAT(pi.name) as pizzaNames " +
            "FROM pizza_order po " +
            "INNER JOIN customer cu ON po.id_customer = cu.id_customer " +
            "INNER JOIN order_item oi ON po.id_order = oi.id_order " +
            "INNER JOIN pizza pi ON oi.id_pizza = pi.id_pizza " +
            "WHERE po.id_order = :orderId " +
            "GROUP BY po.id_order, cu.name, po.date, po.total", nativeQuery = true)
    OrderSummary findSummary(@Param("orderId") int orderId);
}
