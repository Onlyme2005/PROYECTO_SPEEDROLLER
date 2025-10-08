package com.speedroller.app_v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public String mostrarDashboard() {
        return "admin_dashboard"; // busca admin_dashboard.html en templates
    }
}


