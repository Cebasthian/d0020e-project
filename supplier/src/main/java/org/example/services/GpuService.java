package org.example.services;

import org.example.entity.GPU;
import org.example.entity.Materials;
import org.example.repository.GpuRepository;
import org.example.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GpuService {

    @Autowired
    private GpuRepository gpuRepository;

    @Autowired
    private MaterialRepository materialRepository;

    public List<GPU> getAllGpus() {
        return gpuRepository.findAll();
    }

    public GPU getGpuById(int id) {
        return gpuRepository.findByIdWithMaterials(id).orElse(null);
    }

    @Transactional
    public GPU saveGpu(GPU gpu) throws Exception {
        List<Materials> updatedMaterials = new ArrayList<>();

        for (Materials material : gpu.getMaterials()) {
            if (material.getId() != null && material.getId() > 0) {
                Materials existingMaterial = materialRepository.findById(material.getId())
                        .orElseThrow(() -> new Exception("Material not found"));
                existingMaterial.getGpus().add(gpu);
                updatedMaterials.add(existingMaterial);
            } else {
                if (material.getMaterialType() == null || material.getMaterialType().isEmpty()) {
                    throw new Exception("MaterialType is required for new materials.");
                }
                material.getGpus().add(gpu);
                material = materialRepository.save(material);
                updatedMaterials.add(material);
            }
        }

        gpu.setMaterials(updatedMaterials);
        GPU savedGpu = gpuRepository.save(gpu);
        return gpuRepository.findByIdWithMaterials(savedGpu.getId())
                .orElseThrow(() -> new Exception("GPU not found after save"));
    }

    public void deleteGpu(int id) {
        gpuRepository.deleteById(id);
    }

}
