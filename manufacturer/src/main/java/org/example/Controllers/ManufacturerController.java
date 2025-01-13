package org.example.Controllers;

import org.example.Entity.PC;
import org.example.Services.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/GET_PCbyID/{ID}")
    public PC GET_PCbyID(@PathVariable int ID){
        return manufacturerService.findByID(ID);
    }

    // lägg till och posta PC
    @PostMapping("/POST_PCs")
    public void POST_PCs(PC temp){
        manufacturerService.addPC(temp);
    }

    @PostMapping("/POST_PCbyID")
    public PC POST_PCbyID(int ID){
        return manufacturerService.findByID(ID);
    }

}
