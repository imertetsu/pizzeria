package com.pizzeria.persistence.repository;

import com.pizzeria.persistence.entity.PizzaEntity;
import com.pizzeria.service.dto.UpdatePizzaPriceDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {
    //esto es un query method a sql en tiempo de ejecucion que haria referencia a esta consulta:
    // select * from pizza where available = 1 order by price
    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();
    PizzaEntity findAllByAvailableTrueAndNameIgnoreCase(String name);
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainsIgnoreCase(String description);
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainsIgnoreCase(String description);
    int countByVeganTrue();

    //esta es lo mismo que lo otro, solamente que en el otro estamos utilizando un objecto
    /*@Query(value = "UPDATE pizza SET price = :newPrice WHERE id_pizza = :pizzaId", nativeQuery = true)
    void updatePizzaPrice(@Param("pizzaId") int pizzaId, @Param("newPrice") double newPrice);*/

    //SEL Spring Expresion Language, para utilizar objectos en query se necesitan unos caracteres especiales que lo leera el SEL
    //los caracteres especiales son :#{#Object.property}
    @Query(value =
            "UPDATE pizza " +
            "SET price = :#{#newPizzaPrice.newPrice} " +
            "WHERE id_pizza = :#{#newPizzaPrice.idPizza}", nativeQuery = true)
    @Modifying //esta anotacion es para realizar cambios en la base de datos INSERT UPDATE DELETE
    void updatePizzaPrice(@Param("newPizzaPrice")UpdatePizzaPriceDTO newPizzaPrice);
}
