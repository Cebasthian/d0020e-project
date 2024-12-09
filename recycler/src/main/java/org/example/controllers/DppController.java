package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.models.RecycleInstructionsEntry;
import org.example.services.DppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "digital product passport")
@RestController
@RequestMapping("/dpp")
public class DppController {

    @Autowired
    private DppService dppService;

    @Operation(summary = "Get the product passport using its productId.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Found product", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content)
    })
    @GetMapping("/{productId}")
    public String getDppData(@PathVariable String productId) {
        dppService.getDppData(productId);
        return "placeholder";
    }
}
