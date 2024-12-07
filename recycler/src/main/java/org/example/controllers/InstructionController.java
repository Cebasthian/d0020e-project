package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.exceptions.EntryAlreadyExistsException;
import org.example.exceptions.NoSuchEntryException;
import org.example.repositories.InstructionRepository;
import org.example.models.RecycleInstructionsEntry;
import org.example.services.InstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructions")
public class InstructionController {


    @Autowired
    private InstructionService instructionService;

    @Operation(summary = "Get all entries in the database.")
    @GetMapping("/get-entries")
    public List<RecycleInstructionsEntry> getAllInstructionsEntries() {
        return instructionService.getAllEntries();
    }

    @Operation(summary = "Get a specific entry using material id.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Found instructions", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RecycleInstructionsEntry.class))}),
        @ApiResponse(responseCode = "404", description = "Instructions entry not found", content = @Content)
    })
    @GetMapping("/get/{materialId}")
    public RecycleInstructionsEntry getInstructionsByMaterial(@PathVariable String materialId) throws NoSuchEntryException {
        return instructionService.getInstructionsEntry(materialId);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Found instructions", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RecycleInstructionsEntry.class))}),
            @ApiResponse(responseCode = "400", description = "Instruction already exists", content = @Content)
    })
    @PostMapping("/create-entry")
    public RecycleInstructionsEntry createInstructionsEntry(@RequestBody String materialId) throws EntryAlreadyExistsException {
        return instructionService.createInstructionsEntry(materialId);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Updated instructions entry", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RecycleInstructionsEntry.class))}),
            @ApiResponse(responseCode = "404", description = "Instructions entry not found", content = @Content)
    })
    @PutMapping("/update-entry")
    public RecycleInstructionsEntry updateInstructionsEntry(@RequestBody RecycleInstructionsEntry entry) throws NoSuchEntryException {
        return instructionService.updateInstructionsEntry(entry);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Deleted instruction entry"),
            @ApiResponse(responseCode = "404", description = "Instructions entry not found", content = @Content)
    })
    @DeleteMapping("/delete-entry/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInstructionsEntry(@PathVariable Integer id) throws NoSuchEntryException {
        instructionService.deleteInstructionsEntry(id);
    }
}
