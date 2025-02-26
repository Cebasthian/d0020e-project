package org.example.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


import org.example.Entity.PC;
import org.example.Services.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/PCs")
public class ManufacturerController {

    @Autowired
    private ManufacturerService manufacturerService;

    @Operation(summary = "Adminpage", description = "Retrieve the list of all PCs and add it to the model")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description ="Successfully added PC",content =
    @Content(mediaType = "application/json", schema = @Schema(implementation = PC.class))),
    @ApiResponse(responseCode = "500", description = "Error")
})
    //koden för HTML.
    @GetMapping("/admin")
    public String getAdminPage() {
        return "admin";
    }


    @Operation(summary = "Get all PCs", description = "Retrieve the list of all PCs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description ="Successfully retrieved PCs",content =
            @Content(mediaType = "application/json", schema = @Schema(implementation = PC.class))),
            @ApiResponse(responseCode = "500", description = "Error")
    })

    @GetMapping("/GET-All-PCs")
    public List<PC> GET_PCs (){
       return manufacturerService.findAll();
    }

    @Operation(summary = "Get PC", description = "Retrieve a certain PC")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description ="Successfully retrieved a PC",content =
            @Content(mediaType = "application/json", schema = @Schema(implementation = PC.class))),
            @ApiResponse(responseCode = "500", description = "Error")
    })

    @GetMapping("/GET/{ID}")
    public Optional<PC> GET_PCbyID(@PathVariable Long ID){
        return manufacturerService.findbyID(ID);
    }

    @Operation(summary = "Create PC", description = "Create a PC")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description ="Successfully created a PC",content =
            @Content(mediaType = "application/json", schema = @Schema(implementation = PC.class))),
            @ApiResponse(responseCode = "500", description = "Error")
    })

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

    @Operation(summary = "Update PC", description = "Update information about PC")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description ="Successfully updated PC",content =
            @Content(mediaType = "application/json", schema = @Schema(implementation = PC.class))),
            @ApiResponse(responseCode = "500", description = "Error")
    })

    @PutMapping("/update-pc/{id}")
    public void updatePC(@PathVariable Long id, @RequestParam(required = false) String productId, @RequestParam(required = false) String energyClass, @RequestParam(required = false) String dimensions, @RequestParam(required = false) String lifecycle, @RequestParam(defaultValue = "0") int powerRating, @RequestParam(required = false) String installingInstructions, @RequestParam(required = false) String maintenanceInstructions, @RequestParam(required = false) String repairInstructions, @RequestParam(required = false) String assemblyCarbonFootprint, @RequestParam(required = false) String warranty, @RequestParam(defaultValue = "0") int idForComponent, @RequestParam(required = false) String componentType, @RequestParam(required = false) String componentId, @RequestParam(required = false) String componentName) {
        manufacturerService.updatePCField(id, productId, energyClass, dimensions, lifecycle, powerRating, installingInstructions, maintenanceInstructions, repairInstructions, assemblyCarbonFootprint, warranty, idForComponent, componentType, componentId, componentName);

    }


    @Operation(summary = "Delete PC", description = "Remove a PC from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description ="Successfully removed a PC",content =
            @Content(mediaType = "application/json", schema = @Schema(implementation = PC.class))),
            @ApiResponse(responseCode = "500", description = "Error")
    })

    @DeleteMapping("/delete/{ID}")
    public void deletePC(@PathVariable Long ID) {
        manufacturerService.deletePCbyID(ID);
    }
}
