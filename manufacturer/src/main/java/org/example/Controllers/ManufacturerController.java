package org.example.Controllers;

import org.example.Entity.PC;
import org.example.Services.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
// @RequestMapping("/pc") // genom att ha denna annotation så tvingar den alla endpoints att börja med /pc. Alltså kommer en endpoint att vara: http://localhost:8080/pc/get-all
public class ManufacturerController {

    @Autowired
    private ManufacturerService manufacturerService;

    //koden för HTML.
    @GetMapping("/")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/GET-All-PCs")
    public List<PC> GET_PCs (){
       return manufacturerService.findALL();
    }

    @GetMapping("/GET/{ID}")
    public PC GET_PCbyID(@PathVariable int ID){
        return manufacturerService.findByID(ID);
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

    // Saknar en update metod
   @PutMapping("/update-pc")
    public void updatePC(@RequestBody PC pc) {
        manufacturerService.updatePC(pc);
    }

    // Saknar en delete metod
    @DeleteMapping("/delete/{id}")
    public void deletePC(@PathVariable Integer ID) {
        manufacturerService.deletePCbyID(ID);
    }
}
