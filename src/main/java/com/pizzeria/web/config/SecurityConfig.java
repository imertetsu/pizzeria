package com.pizzeria.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                customizeRequests -> {
                    customizeRequests
                            //aca ya no ponemos /api/ porque esa es la raiz, se pone directamente la ruta del controlador
                            //con un * solo permitimos el primer nivel con ** permitimos todo para adelante de la ruta
                            .requestMatchers(HttpMethod.GET, "/*").permitAll()
                            .requestMatchers(HttpMethod.PUT).denyAll()
                            .anyRequest().authenticated();
                }).csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .cors(Customizer.withDefaults());
        return httpSecurity.build();
    }
}
