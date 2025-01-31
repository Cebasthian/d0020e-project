package org.example.Controllers;

import org.example.Entity.PC;
import org.example.Services.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
public class ManufacturerController {

    @Autowired
    private ManufacturerService manufacturerService;

    //koden för HTML.
    @GetMapping("/")
    public String adminPage(Model model) {
        // Retrieve the list of all PCs and add it to the model
        model.addAttribute("pcs", manufacturerService.findAll());
        return "admin";  // Thymeleaf will render this HTML file
    }

    @GetMapping("/GET-All-PCs")
    public List<PC> GET_PCs (){
       return manufacturerService.findAll();
    }

    @GetMapping("/GET/{ID}")
    public PC GET_PCbyID(@PathVariable int ID){
        return manufacturerService.findbyID(ID);
    }

    // lägg till och posta PC
    @PostMapping("/CREATE_PCs")
    public void POST_PCs(@RequestBody PC temp){
        manufacturerService.addPC(temp);
    }

    // Det makear inte sense att skapa en PC om man vet id redan innan. Så ev ta bort den här metoden
    //@PostMapping("/POST_PCbyID")
    //public PC POST_PCbyID(int ID){
        //return manufacturerService.findByID(ID);
    //}

   @PutMapping("/update-pc")
    public void updatePC(@RequestBody PC pc) {
        manufacturerService.updatePC(pc);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePC(@PathVariable Integer ID) {
        manufacturerService.deletePCbyID(ID);
    }
}
