package org.example.controllers;

import org.example.repositories.InstructionRepository;
import org.example.models.RecycleInstructionsEntry;
import org.example.services.InstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instruction")
public class InstructionController {


    @Autowired
    private InstructionService instructionService;

    @GetMapping("/getAll")
    public Iterable<RecycleInstructionsEntry> getAllInstructionsEntries() {
        return instructionService.getAllEntries();
    }

    @GetMapping("/instructions/{materialId}")
    public RecycleInstructionsEntry getInstructionsByMaterial(@PathVariable String materialId) {
        return instructionService.getInstructionsEntry(materialId);
    }

    @PostMapping("/create")
    public RecycleInstructionsEntry createInstructionsEntry(@RequestBody String materialId) {
        return instructionService.createInstructionsEntry(materialId);
    }

    @PutMapping("/update")
    public void updateInstructionsEntry(@RequestBody RecycleInstructionsEntry entry) {
        instructionService.updateInstructionsEntry(entry);
    }

    @DeleteMapping("/delete")
    public void deleteInstructionsEntry(@RequestBody Integer id) {
        instructionService.deleteInstructionsEntry(id);
    }

    // Nedanför kanske är onödiga då vi har en PUT för update så man kan använda bara den istället för att komplicera det med fler endpoints.
    @PutMapping("/addInstruction")
    public void addInstruction() {

    }
    @PutMapping("/addManyInstructions")
    public void addManyInstructions() {

    }
    @PutMapping("/removeInstruction")
    public void removeInstruction() {

    }
    @PutMapping("/reorderInstructions")
    public void reorderInstructions() {

    }

}
