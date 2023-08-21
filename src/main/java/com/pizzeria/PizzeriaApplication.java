package com.pizzeria;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing //para poder conocer que datos cambiaron, quien cambio, etc para aplicar a los Entities
public class PizzeriaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PizzeriaApplication.class, args);
		System.out.println("hola mundo");
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
