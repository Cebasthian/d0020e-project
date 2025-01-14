package org.example.Controllers;

import org.example.Entity.PC;
import org.example.Services.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
// @RequestMapping("/pc") // genom att ha denna annotation så tvingar den alla endpoints att börja med /pc. Alltså kommer en endpoint att vara: http://localhost:8080/pc/get-all
public class ManufacturerController {

    @Autowired
    private ManufacturerService manufacturerService;

    @GetMapping("/GET_PCs")
    // @GetMapping("/get-all") imo är det bättre att ha mer beskrivande endpoints.
    public List<PC> GET_PCs (){
       return manufacturerService.findALL();
    }

    @GetMapping("/GET_PCbyID/{ID}")
    // @GetMapping("/get/{id}")
    public PC GET_PCbyID(@PathVariable int ID){
        return manufacturerService.findByID(ID);
    }

    // lägg till och posta PC
    @PostMapping("/POST_PCs")
    // @PostMapping("/create")
    // behövs också @RequestBody innan 'PC temp' (spring boot syntax)
    public void POST_PCs(PC temp){
        manufacturerService.addPC(temp);
    }

    // Det makear inte sense att skapa en PC om man vet id redan innan. Så ev ta bort den här metoden
    @PostMapping("/POST_PCbyID")
    public PC POST_PCbyID(int ID){
        return manufacturerService.findByID(ID);
    }

    // Saknar en update metod
//    @PutMapping("/update-pc")
//    public PC updatePC(@RequestBody PC pc) {
//        return manufacturerService.updatePC(pc);
//    }

    // Saknar en delete metod
//    @DeleteMapping("/delete/{id}")
//    public void deletePC(@PathVariable Integer ID) {
//        manufacturerService.deletePCbyID(ID);
//    }
}
