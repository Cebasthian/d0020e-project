package org.example.Controllers;

import org.example.Services.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ManufacturerController {

    @Autowired
    private ManufacturerService manufacturerService;

    @GetMapping("/GET_PCs")
    public void GET_PCs (){
       //return manufacturerService.findALL();
    }

    @GetMapping("/GET_PCbyID")
    public void GET_PCbyID(){
        //return manufacturerService.findByID();
    }

    @PostMapping("/POST_PCs")
    public void POST_PCs(){

    }

    @PostMapping("/POST_PCbyID")
    public void POST_PCbyID(){

    }

}
