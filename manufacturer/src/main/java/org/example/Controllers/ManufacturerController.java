package org.example.Controllers;

import org.example.Entity.PC;
import org.example.Services.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ManufacturerController {

    @Autowired
    private ManufacturerService manufacturerService;

    @GetMapping("/GET_PCs")
    public List<PC> GET_PCs (){
       return manufacturerService.findALL();
    }

    @GetMapping("/GET_PCbyID")
    public PC GET_PCbyID(int ID){
        return manufacturerService.findByID(ID);
    }

    @PostMapping("/POST_PCs")
    public List<PC> POST_PCs(){
        return manufacturerService.addPC();
    }

    @PostMapping("/POST_PCbyID")
    public PC POST_PCbyID(){
        return manufacturerService.findByID();
    }

}
