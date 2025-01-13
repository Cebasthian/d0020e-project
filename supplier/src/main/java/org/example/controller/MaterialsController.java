package org.example.controller;

// Swagger documentation
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

// Spring boot
import org.example.entity.Materials;
import org.example.services.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Material Management", description = "Endpoints for managing materials")
@RestController
@RequestMapping("/materials")
public class MaterialsController {

    @Autowired
    private MaterialService materialService;

    @Operation(summary = "Get all Materials", description = "Retrieve a list of all materials")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of materials retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Materials.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<Materials> getAllMaterials() {
        return materialService.getAllMaterials();
    }

    @Operation(summary = "Get Material by ID", description = "Retrieve a specific material by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Material retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Materials.class))),
            @ApiResponse(responseCode = "404", description = "Material not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Materials> getMaterialById(@PathVariable int id) {
        Materials materials = materialService.getMaterialById(id);
        if (materials != null) {
            return ResponseEntity.ok(materials);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Add a new Material", description = "Create a new material record")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Material created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Materials.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public Materials createOrUpdateMaterial(@RequestBody Materials materials) {
        return materialService.saveMaterial(materials);
    }

    @Operation(summary = "Delete Material", description = "Delete a material by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Material deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Material not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable int id) {
        materialService.deleteMaterials(id);
        return ResponseEntity.noContent().build();
    }
}
