package com.pizzeria.web.controller;

import com.pizzeria.persistence.entity.PizzaEntity;
import com.pizzeria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/availables")
    public ResponseEntity<List<PizzaEntity>> getPizzasAvailables(){
        return ResponseEntity.ok(this.pizzaService.getPizzasAvailables());
    }
    @GetMapping("/{id}")
    public ResponseEntity<PizzaEntity> getPizzaById(@PathVariable("id") int idPizza){
        if (this.pizzaService.pizzaExists(idPizza)){
            PizzaEntity pizza = this.pizzaService.getPizza(idPizza);
            return ResponseEntity.ok(pizza);
        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping("name/{pizzaName}")
    public ResponseEntity<PizzaEntity> getPizzaAvailableByName(@PathVariable String pizzaName){
        return ResponseEntity.ok(this.pizzaService.getPizzaByName(pizzaName));
    }

    @GetMapping("/ingredient/{description}")
    public ResponseEntity<List<PizzaEntity>> getPizzaAvailableByDescription(@PathVariable String description){
        return ResponseEntity.ok(this.pizzaService.getPizzaByDescription(description));
    }

    @GetMapping("/excluding/{description}")
    public ResponseEntity<List<PizzaEntity>> getPizzasExcludingByDescription(@PathVariable String description){
        return ResponseEntity.ok(this.pizzaService.getPizzasExcludingByDescription(description));
    }

    @PostMapping()
    public ResponseEntity<PizzaEntity> savePizza(@RequestBody PizzaEntity pizzaEntity){
        if(pizzaEntity.getIdPizza() == null || !this.pizzaService.pizzaExists(pizzaEntity.getIdPizza())){
            return ResponseEntity.ok(this.pizzaService.save(pizzaEntity));
        }
        return ResponseEntity.badRequest().build();
    }
    @PutMapping()
    public ResponseEntity<PizzaEntity> updatePizza(@RequestBody PizzaEntity pizzaEntity){
        if(pizzaEntity.getIdPizza() != null){
            return ResponseEntity.ok(this.pizzaService.save(pizzaEntity));
        }
        return ResponseEntity.badRequest().build();
    }
    @DeleteMapping("/{idPizza}")
    public ResponseEntity<Void> deletePizzaById(@PathVariable int idPizza){
        if(this.pizzaService.pizzaExists(idPizza)){
            this.pizzaService.deletePizza(idPizza);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
