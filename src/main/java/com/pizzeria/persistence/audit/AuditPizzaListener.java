package com.pizzeria.persistence.audit;

import com.pizzeria.persistence.entity.PizzaEntity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.springframework.util.SerializationUtils;

public class AuditPizzaListener {
    private PizzaEntity currentValue;
    @PostLoad//se ejecuta cuando un select obtenga algunos datos
    public void postLoad(PizzaEntity pizzaEntity){
        System.out.println("POST LOAD");

        //este procesos se tiene que hacer porque al hacer solo this.currentValue = pizzaEntity;
        //por como se comporta la memoria de java al cargar los objectos, al utilizar otros metodo se sobrecarga la posicion en memoria
        this.currentValue = SerializationUtils.clone(pizzaEntity);
    }
    @PostPersist //despues de una creacion de una pizza
    @PostUpdate
    public void onPostPersist(PizzaEntity pizzaEntity){
        System.out.println("POST PERSIST OR UPDATE");
        System.out.println("OLD VALUE:: " + this.currentValue);
        System.out.println("NEW VALUE" + pizzaEntity.toString());
    }
    @PreRemove
    public void onPreDelete(PizzaEntity pizzaEntity){
        System.out.println(pizzaEntity.toString());
    }
}
