package com.pizzeria.service;

import com.pizzeria.persistence.entity.PizzaEntity;
import com.pizzeria.persistence.repository.PizzaPagSortRepository;
import com.pizzeria.persistence.repository.PizzaRepository;
import com.pizzeria.service.dto.UpdatePizzaPriceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//import java.awt.print.Pageable;
import java.util.List;

@Service
public class PizzaService {

    //private final JdbcTemplate jdbcTemplate; //para hacer consultas rapidas
    private final PizzaRepository pizzaRepository;
    private final PizzaPagSortRepository pizzaPagSortRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }
    public List<PizzaEntity> getAllPizzas(){
        return this.pizzaRepository.findAll();
    }
    public Page<PizzaEntity> getPizzasByPages(int page, int elements){
        //return this.jdbcTemplate.query("SELECT * FROM pizza;", new BeanPropertyRowMapper<>(PizzaEntity.class));
        System.out.println(this.pizzaRepository.countByVeganTrue());
        Pageable pageable = (Pageable) PageRequest.of(page, elements);
        return this.pizzaPagSortRepository.findAll(pageable);
    }
    public Page<PizzaEntity> getPizzasAvailables(int page, int elements, String sortBy, String sortDirection){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = (Pageable) PageRequest.of(page, elements, sort);//Sort.by(sortBy));
        return this.pizzaPagSortRepository.findByAvailableTrue(pageable);
    }
    public PizzaEntity getPizza(int idPizza){
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }
    public PizzaEntity getPizzaByName(String name){
        return this.pizzaRepository.findAllByAvailableTrueAndNameIgnoreCase(name);
    }
    @Secured("ROLE_ADMIN")
    public PizzaEntity save(PizzaEntity pizzaEntity){
        return this.pizzaRepository.save(pizzaEntity);
    }

    //la anotacion @Transactional nos garantiza las propiedades ACID que se deben cumplir a la hora de realizar una transaccion
    //Atomicity, Consistency, Isolation, Durability, es decir que no queden a medias, realizan rollback si algo falla etc.
    //@Transactional de springframework no confundir
    @Secured("ROLE_ADMIN")
    @Transactional//(noRollbackFor = EmailApiException.class, propagation = Propagation.REQUIRED)
    public void updatePizzaPrice(UpdatePizzaPriceDTO pizzaPriceDTO){
        this.pizzaRepository.updatePizzaPrice(pizzaPriceDTO);
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
    @Secured("ROLE_ADMIN")
    public void deletePizza(int idPizza){
        this.pizzaRepository.deleteById(idPizza);
    }
}
