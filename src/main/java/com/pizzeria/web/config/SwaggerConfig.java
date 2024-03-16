package com.pizzeria.web.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .addSecurityItem(new SecurityRequirement().addList("bearer-key"))
                .info(new Info()
                        .title("API Documentation")
                        .version("1.0")
                        .description("""
                                API to manage customers, pizzas, orders and users of a pizzeria.
                                Here are some recommendations to use the API, there are roles for a user examples:
                                 * ADMIN
                                 * CUSTOMER
                                 * CHEF
                                \n To execute an endpoint with a secured role, you need to login with the user who has the required role.
                                \n Each endpoint has a description where you can see which role is required
                                \n After you log in with the correct credentials, a token is retrieved for you to use and configure on the green Authorize button below this section. 👇""")
                        .contact(new Contact().name("Imer Coaguila").url("https://portafoliotetsu.web.app").email("imertpro@gmail.com")));
    }
}
