package com.pizzeria.web.controller;

import com.pizzeria.service.dto.LoginDTO;
import com.pizzeria.web.config.JwtUtil;
//import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication Controller", description = "In this section we are going to login with a user")
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    @Autowired
    public AuthController(
            AuthenticationManager authenticationManager,
            JwtUtil jwtUtil){
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }
    @Operation(summary = "Login a user. ROLE NOT REQUIRED", description = "You can log in with username and password.")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO){
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
        System.out.println("LOGIN " + login);
        Authentication authentication = this.authenticationManager.authenticate(login);

        System.out.println("Esta Auth? "+authentication.isAuthenticated());
        //System.out.println(authentication.getPrincipal());
        String jwt = this.jwtUtil.create(loginDTO.getUsername());
        //System.out.println("JWT: "+jwt);
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt).body(jwt);
    }
}
