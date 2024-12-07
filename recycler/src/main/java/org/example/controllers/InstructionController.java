package org.example.controllers;

import org.example.repositories.InstructionRepository;
import org.example.models.RecycleInstructionsEntry;
import org.example.services.InstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructions")
public class InstructionController {


    @Autowired
    private InstructionService instructionService;

    @GetMapping("/get-entries")
    public Iterable<RecycleInstructionsEntry> getAllInstructionsEntries() {
        return instructionService.getAllEntries();
    }

    @GetMapping("/get/{materialId}")
    public RecycleInstructionsEntry getInstructionsByMaterial(@PathVariable String materialId) {
        return instructionService.getInstructionsEntry(materialId);
    }

    @PostMapping("/create-entry")
    public RecycleInstructionsEntry createInstructionsEntry(@RequestBody String materialId) {
        return instructionService.createInstructionsEntry(materialId);
    }

    @PutMapping("/update-entry")
    public RecycleInstructionsEntry updateInstructionsEntry(@RequestBody RecycleInstructionsEntry entry) {
        return instructionService.updateInstructionsEntry(entry);
    }

    @DeleteMapping("/delete-entry/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInstructionsEntry(@PathVariable Integer id) {
        instructionService.deleteInstructionsEntry(id);
    }
}
