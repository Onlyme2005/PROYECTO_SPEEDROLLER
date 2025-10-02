package com.speedroller.app_v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index"; // Renderiza templates/index.html
    }

    @GetMapping("/corporativo")
    public String corporativo() {
        return "index"; // Puedes crear una página separada luego
    }

    @GetMapping("/mision")
    public String mision() {
        return "index";
    }

    @GetMapping("/vision")
    public String vision() {
        return "index";
    }

    @GetMapping("/valores")
    public String valores() {
        return "index";
    }

    @GetMapping("/servicios")
    public String servicios() {
        return "index";
    }

    @GetMapping("/eventos")
    public String eventos() {
        return "index";
    }

}