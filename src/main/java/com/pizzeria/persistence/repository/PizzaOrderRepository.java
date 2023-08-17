package com.pizzeria.persistence.repository;

import com.pizzeria.persistence.entity.PizzaOrderEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PizzaOrderRepository extends ListCrudRepository<PizzaOrderEntity, Integer> {
    List<PizzaOrderEntity> findAllByDateAfter(LocalDateTime localDateTime);
    List<PizzaOrderEntity> findAllByMethodIn(List<String> methods);
}
