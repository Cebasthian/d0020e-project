package org.example.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Admin {
    @GetMapping("/")
    public String adminPage() {
        return "admin";
    }
}
