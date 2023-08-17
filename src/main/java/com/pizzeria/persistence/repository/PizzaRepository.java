package com.pizzeria.persistence.repository;

import com.pizzeria.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {
    //esto es un query method a sql en tiempo de ejecucion que haria referencia a esta consulta:
    // select * from pizza where available = 1 order by price
    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();
    PizzaEntity findAllByAvailableTrueAndNameIgnoreCase(String name);
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainsIgnoreCase(String description);
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainsIgnoreCase(String description);
    int countByVeganTrue();
}
