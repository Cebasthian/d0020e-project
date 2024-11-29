package org.example.controllers;

import org.example.services.DppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DppController {

    @Autowired
    private DppService dppService;

    @GetMapping("/dpp/{dppId}")
    public void getDppData(@PathVariable String dppId) {
        dppService.getDppData(dppId);
    }
}
