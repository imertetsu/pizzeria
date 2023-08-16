package com.pizzeria.persistence.repository;

import com.pizzeria.persistence.entity.PizzaOrderEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface PizzaOrderRepository extends ListCrudRepository<PizzaOrderEntity, Integer> {
}
