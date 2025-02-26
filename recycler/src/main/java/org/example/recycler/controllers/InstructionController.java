package org.example.recycler.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.recycler.exceptions.EntryAlreadyExistsException;
import org.example.recycler.exceptions.NoSuchEntryException;
import org.example.recycler.models.RecycleInstructionsEntry;
import org.example.recycler.services.InstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Instructions", description = "For managing recycling instructions.")
@RestController
@CrossOrigin
@RequestMapping("/instructions")
public class InstructionController {


    @Autowired
    private InstructionService instructionService;

    @Operation(summary = "Get all entries in the database.")
    @GetMapping("/get")
    public List<RecycleInstructionsEntry> getAllInstructionsEntries() {
        return instructionService.getAllEntries();
    }

    @Operation(summary = "Get an entry using material.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Found instructions", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RecycleInstructionsEntry.class))}),
        @ApiResponse(responseCode = "404", description = "Instructions entry not found", content = @Content)
    })
    @GetMapping("/get/{material}")
    public RecycleInstructionsEntry getInstructionsByMaterial(@PathVariable String material) throws NoSuchEntryException {
        return instructionService.getInstructionsEntry(material);
    }

    @Operation(summary = "Create a new entry.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Found instructions", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RecycleInstructionsEntry.class))}),
            @ApiResponse(responseCode = "400", description = "Instruction already exists", content = @Content)
    })
    @PostMapping("/create-entry")
    public RecycleInstructionsEntry createInstructionsEntry(@RequestBody String materialId) throws EntryAlreadyExistsException {
        return instructionService.createInstructionsEntry(materialId);
    }

    @Operation(summary = "Update an entry.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Updated instructions entry", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RecycleInstructionsEntry.class))}),
            @ApiResponse(responseCode = "404", description = "Instructions entry not found", content = @Content)
    })
    @PutMapping("/update-entry")
    public RecycleInstructionsEntry updateInstructionsEntry(@RequestBody RecycleInstructionsEntry entry) throws NoSuchEntryException {
        return instructionService.updateInstructionsEntry(entry);
    }

    @Operation(summary = "Delete an entry using its id")
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
