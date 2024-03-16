package com.pizzeria.web.controller;

import com.pizzeria.persistence.entity.PizzaEntity;
import com.pizzeria.service.PizzaService;
import com.pizzeria.service.dto.UpdatePizzaPriceDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Pizza Controller", description = "In this section we are going to create, read, update and delete Pizzas")
@RestController
@RequestMapping("/pizzas")
public class PizzaController {
    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @Operation(summary = "Get all pizzas", description = "We are going to retrieve a list of all pizzas")
    @GetMapping()
    public ResponseEntity<List<PizzaEntity>> getAll(){
        return ResponseEntity.ok(this.pizzaService.getAllPizzas());
    }

    @Operation(summary = "Get pizzas by Pages and Elements", description = "We are going to retrieve a list of pizzas by setting the Page Number and the Elements to show")
    @GetMapping("/getByPages")
    //@CrossOrigin(origins = { "http://localhost:4200/"}) //esto es para especificar el endpoint
    public ResponseEntity<Page<PizzaEntity>> getPizzas(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "4") int elements){
        return ResponseEntity.ok(this.pizzaService.getPizzasByPages(page, elements));
    }
    @Operation(summary = "Get pizzas available by Pages and Elements", description = "We are going to retrieve a list of pizzas available by setting the Page Number and the Elements to show")
    @GetMapping("/availables")
    public ResponseEntity<Page<PizzaEntity>> getPizzasAvailables(@RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "4") int elements,
                                                                 @RequestParam(defaultValue = "price") String sortBy,
                                                                 @RequestParam(defaultValue = "ASC") String sortDirection){
        return ResponseEntity.ok(this.pizzaService.getPizzasAvailables(page, elements, sortBy, sortDirection));
    }
    @Operation(summary = "Get a pizza by id", description = "We are going to retrieve a pizza by its id")
    @GetMapping("/{id}")
    public ResponseEntity<PizzaEntity> getPizzaById(@PathVariable("id") int idPizza){
        if (this.pizzaService.pizzaExists(idPizza)){
            PizzaEntity pizza = this.pizzaService.getPizza(idPizza);
            return ResponseEntity.ok(pizza);
        }
        return ResponseEntity.badRequest().build();
    }
    @Operation(summary = "Get a pizza by name", description = "We are going to retrieve a pizza by its name")
    @GetMapping("name/{pizzaName}")
    public ResponseEntity<PizzaEntity> getPizzaAvailableByName(@PathVariable String pizzaName){
        return ResponseEntity.ok(this.pizzaService.getPizzaByName(pizzaName));
    }
    @Operation(summary = "Get pizzas by ingredient", description = "We are going to retrieve a list of pizzas by sending ingredients")
    @GetMapping("/ingredient/{description}")
    public ResponseEntity<List<PizzaEntity>> getPizzaAvailableByDescription(@PathVariable String description){
        return ResponseEntity.ok(this.pizzaService.getPizzaByDescription(description));
    }
    @Operation(summary = "Get pizzas excluding ingredient", description = "We are going to retrieve a list of pizzas by excluding ingredients")
    @GetMapping("/excluding/{description}")
    public ResponseEntity<List<PizzaEntity>> getPizzasExcludingByDescription(@PathVariable String description){
        return ResponseEntity.ok(this.pizzaService.getPizzasExcludingByDescription(description));
    }

    @Operation(summary = "Save a new pizza", description = "We are going to create a new pizza and save it")
    @PostMapping()
    public ResponseEntity<PizzaEntity> savePizza(@RequestBody PizzaEntity pizzaEntity){
        if(pizzaEntity.getIdPizza() == null || !this.pizzaService.pizzaExists(pizzaEntity.getIdPizza())){
            return ResponseEntity.ok(this.pizzaService.save(pizzaEntity));
        }
        return ResponseEntity.badRequest().build();
    }
    @Operation(summary = "Update a pizza", description = "We are going to update a pizza and save it")
    @PutMapping()
    public ResponseEntity<PizzaEntity> updatePizza(@RequestBody PizzaEntity pizzaEntity){
        if(pizzaEntity.getIdPizza() != null){
            return ResponseEntity.ok(this.pizzaService.save(pizzaEntity));
        }
        return ResponseEntity.badRequest().build();
    }
    @Operation(summary = "Update a pizza price", description = "We are going to update a pizza price and save it")
    @PutMapping("/price")
    public ResponseEntity<PizzaEntity> updatePizzaPrice(@RequestBody UpdatePizzaPriceDTO pizzaPriceDTO){
        if(this.pizzaService.pizzaExists(pizzaPriceDTO.getIdPizza())){
            this.pizzaService.updatePizzaPrice(pizzaPriceDTO);
            PizzaEntity pizza = this.pizzaService.getPizza(pizzaPriceDTO.getIdPizza());
            return ResponseEntity.ok(pizza);
        }
        return ResponseEntity.badRequest().build();
    }
    @Operation(summary = "Delete a pizza", description = "We are going to delete a pizza by its id")
    @DeleteMapping("/{idPizza}")
    public ResponseEntity<Void> deletePizzaById(@PathVariable int idPizza){
        if(this.pizzaService.pizzaExists(idPizza)){
            this.pizzaService.deletePizza(idPizza);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
