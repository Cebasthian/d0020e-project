package org.example.controller;

// Swagger

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

//
import org.example.entity.Component;
import org.example.entity.Supplier;
import org.example.services.SupplierModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
*
* End point list:
*
* Suppliers:
* GET /supplier/suppliers
* GET /supplier/suppliers/{id}
* POST /supplier/suppliers
* DELETE /supplier/supplier/{id}
*
* Components:
* GET /supplier/components
* GET /supplier/components/{id}
* POST /supplier/components
* DELETE /supplier/components/{id}
*
*/

@RestController
@RequestMapping("/supplier")
@Tag(name ="Supplier module", description = "APIs to manage supplier and components")
public class SupplierModuleController {

    @Autowired
    private SupplierModuleService  supplierModuleService;


/*
* ##############################################################################
* Supplier:
* ##############################################################################
*/

    @Operation(summary = "Get all suppliers", description = "Retrieve a list of all suppliers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of suppliers retrieved successfully")
    })
    @GetMapping("/suppliers")
    public List<Supplier> getAllSuppliers() {
        return supplierModuleService.getAllSuppliers();
    }
    @Operation(summary = "Get supplier by ID", description = "Retrieve a specific supplier by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Supplier found"),
            @ApiResponse(responseCode = "404", description = "Supplier not found")
    })
    @GetMapping("/suppliers/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable Long id) {
        Supplier supplier = supplierModuleService.getSupplierById(id);

        if (supplier != null) {
            return ResponseEntity.ok(supplier);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Create or update a supplier", description = "Add a new supplier or update an existing one")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Supplier created or updated successfully")
    })
    @PostMapping("/suppliers")
    public Supplier createOrUpdateSupplier(@RequestBody Supplier supplier) {
        return supplierModuleService.saveSupplier(supplier);
    }

    @Operation(summary = "Delete a supplier by ID", description = "Remove a supplier by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Supplier deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Supplier not found")
    })
    @DeleteMapping("/suppliers/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        supplierModuleService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }


/*
* ##############################################################################
* Components: (maybe make separate files...)
* ##############################################################################
*/

    @Operation(summary = "Get all components", description = "Retrieve a list of all components")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of components retrieved successfully")
    })
    @GetMapping("/components")
    public List<Component> getAllComponents() {
        return supplierModuleService.getAllComponents();
    }

    @Operation(summary = "Get component by ID", description = "Retrieve a specific component by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Component found"),
            @ApiResponse(responseCode = "404", description = "Component not found")
    })
    @GetMapping("/coponents/{id}")
    public ResponseEntity<Component> getComponentById(@PathVariable Long id) {
        Component component = supplierModuleService.getComponentById(id);

        if (component != null) {
            return ResponseEntity.ok(component);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Create or update a component", description = "Add a new component or update an existing one")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Component created or updated successfully")
    })
    @PostMapping("/components")
    public Component createOrUpdateComponent(@RequestBody Component component) {
        return supplierModuleService.saveComponent(component);
    }

    @Operation(summary = "Delete a component by ID", description = "Remove a component by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Component deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Component not found")
    })
    @DeleteMapping("/components/{id}")
    public ResponseEntity<Void> deleteComponent(@PathVariable Long id) {
        supplierModuleService.deleteComponent(id);
        return ResponseEntity.noContent().build();
    }
}
