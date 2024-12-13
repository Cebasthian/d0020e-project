package org.example.controller;

import org.example.entity.Materials;
import org.example.services.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materials")
public class MaterialsController {

    @Autowired
    private MaterialService materialService;

    @GetMapping
    public List<Materials> getAllMaterials() {
        return materialService.getAllMaterials();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Materials> getMaterialById(@PathVariable int id) {
        Materials materials = materialService.getMaterialById(id);
        if (materials != null) {
            return ResponseEntity.ok(materials);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Materials createOrUpdateMaterial(@RequestBody Materials materials) {
        return materialService.saveMaterial(materials);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable int id) {
        materialService.deleteMaterials(id);
        return ResponseEntity.noContent().build();
    }
}
