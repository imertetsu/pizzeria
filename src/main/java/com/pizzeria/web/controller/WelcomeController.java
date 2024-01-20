package com.pizzeria.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
public class WelcomeController {
    @ResponseBody
    public ResponseEntity<String> function(){
        return new ResponseEntity<>("Hey welcome", HttpStatus.OK);
    }
}
