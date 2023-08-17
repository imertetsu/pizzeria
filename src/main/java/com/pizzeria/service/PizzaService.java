package com.pizzeria.service;

import com.pizzeria.persistence.entity.PizzaEntity;
import com.pizzeria.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PizzaService {

    //private final JdbcTemplate jdbcTemplate; //para hacer consultas rapidas
    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }
    public List<PizzaEntity> getAllPizzas(){
        //return this.jdbcTemplate.query("SELECT * FROM pizza;", new BeanPropertyRowMapper<>(PizzaEntity.class));
        System.out.println(this.pizzaRepository.countByVeganTrue());
        return this.pizzaRepository.findAll();
    }
    public List<PizzaEntity> getPizzasAvailables(){
        return this.pizzaRepository.findAllByAvailableTrueOrderByPrice();
    }
    public PizzaEntity getPizza(int idPizza){
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }
    public PizzaEntity getPizzaByName(String name){
        return this.pizzaRepository.findAllByAvailableTrueAndNameIgnoreCase(name);
    }
    public PizzaEntity save(PizzaEntity pizzaEntity){
        return this.pizzaRepository.save(pizzaEntity);
    }
    public List<PizzaEntity> getPizzaByDescription(String description){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainsIgnoreCase(description);
    }

    public List<PizzaEntity> getPizzasExcludingByDescription(String description){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainsIgnoreCase(description);
    }

    public boolean pizzaExists(int idPizza){
        return this.pizzaRepository.existsById(idPizza);
    }
    public void deletePizza(int idPizza){
        this.pizzaRepository.deleteById(idPizza);
    }
}
