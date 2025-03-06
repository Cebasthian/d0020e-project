package org.example.services;

import org.example.entity.Materials;
import org.example.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    public List<Materials> getAllMaterials() {
        return materialRepository.findAll();
    }

    public Materials getMaterialById(Integer id) {
        return materialRepository.findById(id).orElse(null);
    }

    public Materials saveMaterial(Materials material) {
        return materialRepository.saveAndFlush(material);
    }

    public void deleteMaterials(Integer id) {
        materialRepository.deleteById(id);
    }
}
