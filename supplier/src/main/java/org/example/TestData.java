package org.example;

import org.example.entity.GPU;
import org.example.entity.Materials;
import org.example.repository.GpuRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class TestData implements ApplicationRunner {


    //    @Autowired
    private final GpuRepository gpuRepository;

    public TestData(GpuRepository gpuRepository) {
        this.gpuRepository = gpuRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Inserting Testing Data for supplier");

        // Insert test data
        GPU gpu = new GPU();
        gpu.setComponentId("RTX3080");
        gpu.setName("NVIDIA RTX 3080");
        gpu.setWeight(800);
        gpu.setDimensions("310x240x75");
        gpu.setPowerRating(650);
        gpu.setInstallingInstructions("Refer to manual");
        gpu.setAssemblyLine("Line B");

        // Create Material 1
        Materials material1 = new Materials();
        material1.setMaterialType("Plastic");
        material1.setOrigin("China");
        material1.setMaterialFlow("Recycled");
        material1.setSupplier("Supplier A");
        material1.setGpu(gpu); // Associate material with GPU

        // Create Material 2
        Materials material2 = new Materials();
        material2.setMaterialType("Metal");
        material2.setOrigin("USA");
        material2.setMaterialFlow("Manufactured");
        material2.setSupplier("Supplier B");
        material2.setGpu(gpu); // Associate material with GPU

        // Add the materials to the GPU's (rtx 3080) material list.
        gpu.setMaterials(Arrays.asList(material1, material2));

        // Save the GPU to db.
        gpuRepository.save(gpu);

        System.out.println("Done with testing data");
    }
}
