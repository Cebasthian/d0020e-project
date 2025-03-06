package org.example;

import org.example.entity.GPU;
import org.example.entity.Materials;
import org.example.repository.GpuRepository;
import org.example.repository.MaterialRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class TestData implements ApplicationRunner {

    private final GpuRepository gpuRepository;
    private final MaterialRepository materialRepository;

    public TestData(GpuRepository gpuRepository, MaterialRepository materialRepository) {
        this.gpuRepository = gpuRepository;
        this.materialRepository = materialRepository;
    }

    @Override
    @Transactional // ngl läste någonstans att man bör ha med, tror inte det faktiskt spelar någon roll, men det ser inte ut att skada...
    public void run(ApplicationArguments args) {
        System.out.println("Inserting Testing Data");

        // Create GPU
        GPU gpu = new GPU();
        gpu.setComponentId("RTX3080");
        gpu.setName("NVIDIA RTX 3080");
        gpu.setWeight(800);
        gpu.setDimensions("310x240x75");
        gpu.setPowerRating(650);
        gpu.setInstallingInstructions("Refer to manual");
        gpu.setAssemblyLine("Line B");

        // Create and save Material 1
        Materials material1 = new Materials();
        material1.setMaterialType("Plastic");
        material1.setOrigin("China");
        material1.setMaterialFlow("Recycled");
        material1.setSupplier("Supplier A");
        material1 = materialRepository.save(material1);

        // Create and save Material 2
        Materials material2 = new Materials();
        material2.setMaterialType("Metal");
        material2.setOrigin("USA");
        material2.setMaterialFlow("Manufactured");
        material2.setSupplier("Supplier B");
        material2 = materialRepository.save(material2);

        // Associate materials with GPU
        gpu.setMaterials(List.of(material1, material2));

        // Associate GPU with materials
        material1.getGpus().add(gpu);
        material2.getGpus().add(gpu);

        // Save GPU with materials
        gpuRepository.save(gpu);
        System.out.println("Testing data inserted");
    }
}