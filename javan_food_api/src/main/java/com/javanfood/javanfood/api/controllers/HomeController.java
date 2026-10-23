package com.javanfood.javanfood.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String redirect(){
        return "redirect:swagger-ui/index.html";
    }
}
