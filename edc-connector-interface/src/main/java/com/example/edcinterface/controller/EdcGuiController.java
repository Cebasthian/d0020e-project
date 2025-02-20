package com.example.edcinterface.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/edc")
public class EdcGuiController {
    @GetMapping(value={
            "",
            "/",
            "/connectors",
            "/assets",
            "/policies",
            "/contracts",
            "/catalog",
            "/negotiated-contracts",
            "/transfers",
    })
    public String home() {
        return "redirect:/edc/index.html";
    }

}
