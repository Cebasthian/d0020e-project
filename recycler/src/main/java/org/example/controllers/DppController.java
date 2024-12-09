package org.example.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.services.DppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "digital product passport")
@RestController
@RequestMapping("/dpp")
public class DppController {

    @Autowired
    private DppService dppService;

    @GetMapping("/{productId}")
    public void getDppData(@PathVariable String productId) {
        dppService.getDppData(productId);
    }
}
