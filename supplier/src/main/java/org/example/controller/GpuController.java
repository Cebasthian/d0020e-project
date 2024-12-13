package org.example.controller;

import org.example.entity.Gpu;
import org.example.services.GpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gpus")
public class GpuController {

    @Autowired
    private GpuService gpuService;

    @GetMapping
    public List<Gpu> getAllGpus() {
        return gpuService.getAllGpus();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gpu> getGpuById(@PathVariable Integer id) {
        Gpu gpu = gpuService.getGpuById(id);
        if (gpu != null) {
            return ResponseEntity.ok(gpu);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Gpu createOrUpdateGpu(@RequestBody Gpu gpu) {
        return gpuService.saveGpu(gpu);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGpu(@PathVariable Integer id) {
        gpuService.deleteGpu(id);
        return ResponseEntity.noContent().build();
    }
}
