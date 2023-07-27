package com.pizzeria;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PizzeriaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PizzeriaApplication.class, args);
		System.out.println("hola mundo");
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
