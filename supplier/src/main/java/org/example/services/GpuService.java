package org.example.services;

import org.example.entity.GPU;
import org.example.entity.Materials;
import org.example.repository.GpuRepository;
import org.example.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return gpuRepository.findById(id).orElse(null);
    }

    public GPU saveGpu(GPU gpu) throws Exception {
        List<Materials> updatedMaterials = new ArrayList<>();

        for (Materials material : gpu.getMaterials()) {
            if (material.getId() > 0) {
                Optional<Materials> existingMaterial = materialRepository.findById(material.getId());
                if (existingMaterial.isPresent()) {
                    updatedMaterials.add(existingMaterial.get());
                } else {
                    throw new Exception("Material with id " + material.getId() + " not found.");
                }
            } else {
                material.setGpu(gpu);
                updatedMaterials.add(material);
            }
        }
        gpu.setMaterials(updatedMaterials);
        return gpuRepository.save(gpu);
    }

    public void deleteGpu(int id) {
        gpuRepository.deleteById(id);
    }

}
