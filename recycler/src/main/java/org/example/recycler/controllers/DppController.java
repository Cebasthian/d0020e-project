package org.example.recycler.controllers;

import com.example.edcinterface.util.HttpRequester;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.recycler.services.DppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "digital product passport")
@RestController
@CrossOrigin
@RequestMapping("/dpp")
public class DppController {

    @Autowired
    private DppService dppService;

    @Autowired
    private HttpRequester httpRequester;

    @Operation(summary = "Get the product passport using its productId.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Found product", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Object.class))}),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content)
    })
    @GetMapping("/{productId}")
    public Object getDppData(@PathVariable String productId) {
//        dppService.getDppData(productId);

//        return httpRequester.get();
        return "hello world";
    }
}
