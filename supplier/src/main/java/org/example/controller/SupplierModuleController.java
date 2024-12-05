package org.example.controller;

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
public class SupplierModuleController {

    @Autowired
    private SupplierModuleService  supplierModuleService;


/*
* ##############################################################################
* Supplier:
* ##############################################################################
*/

    @GetMapping("/suppliers")
    public List<Supplier> getAllSuppliers() {
        return supplierModuleService.getAllSuppliers();
    }

    @GetMapping("/suppliers/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable Long id) {
        Supplier supplier = supplierModuleService.getSupplierById(id);

        if (supplier != null) {
            return ResponseEntity.ok(supplier);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/suppliers")
    public Supplier createOrUpdateSupplier(@RequestBody Supplier supplier) {
        return supplierModuleService.saveSupplier(supplier);
    }

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


    @GetMapping("/components")
    public List<Component> getAllComponents() {
        return supplierModuleService.getAllComponents();
    }

    @GetMapping("/coponents/{id}")
    public ResponseEntity<Component> getComponentById(@PathVariable Long id) {
        Component component = supplierModuleService.getComponentById(id);

        if (component != null) {
            return ResponseEntity.ok(component);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/components")
    public Component createOrUpdateComponent(@RequestBody Component component) {
        return supplierModuleService.saveComponent(component);
    }

    @DeleteMapping("/components/{id}")
    public ResponseEntity<Void> deleteComponent(@PathVariable Long id) {
        supplierModuleService.deleteComponent(id);
        return ResponseEntity.noContent().build();
    }
}
