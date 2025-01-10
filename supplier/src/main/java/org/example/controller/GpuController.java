package org.example.controller;

// Swagger documentation
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

// Spring boot
import org.example.entity.GPU;
import org.example.services.GpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "GPU Management", description = "Endpoints for managing GPUs")
@RestController
@RequestMapping("/gpus")
public class GpuController {

    @Autowired
    private GpuService gpuService;

    @Operation(summary = "Get all GPUs", description = "Retrieve a list of all GPUs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of GPUs retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = GPU.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<GPU> getAllGpus() {
        return gpuService.getAllGpus();
    }

    @Operation(summary = "Get GPU by ID", description = "Retrieve a specific GPU by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "GPU retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = GPU.class))),
            @ApiResponse(responseCode = "404", description = "GPU not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<GPU> getGpuById(@PathVariable Integer id) {
        GPU gpu = gpuService.getGpuById(id);
        if (gpu != null) {
            return ResponseEntity.ok(gpu);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Add a new GPU", description = "Create a new GPU record")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "GPU created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = GPU.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public GPU createOrUpdateGpu(@RequestBody GPU gpu) throws Exception {

        /*if(gpu.getMaterials() != null) {
            gpu.getMaterials().forEach(material -> material.setGpu(gpu));
        }*/
        GPU savedGpu = gpuService.saveGpu(gpu);
        return ResponseEntity.ok(savedGpu).getBody();
    }

    @Operation(summary = "Delete GPU", description = "Delete a GPU by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "GPU deleted successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGpu(@PathVariable Integer id) {
        gpuService.deleteGpu(id);
        return ResponseEntity.noContent().build();
    }
}
