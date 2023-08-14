package com.pizzeria.service;

import com.pizzeria.persistence.entity.PizzaEntity;
import com.pizzeria.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return this.pizzaRepository.findAll();
    }
    public PizzaEntity getPizza(int idPizza){
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }
}
