package com.pizzeria.web.controller;

import com.pizzeria.persistence.entity.PizzaEntity;
import com.pizzeria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {
    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping()
    public ResponseEntity<List<PizzaEntity>> getAll(){
        return ResponseEntity.ok(this.pizzaService.getAllPizzas());
    }
    @GetMapping("/{id}")
    public ResponseEntity<PizzaEntity> getPizzaById(@PathVariable("id") int idPizza){
        PizzaEntity pizza = this.pizzaService.getPizza(idPizza);
        return ResponseEntity.ok(pizza);
    }
}
