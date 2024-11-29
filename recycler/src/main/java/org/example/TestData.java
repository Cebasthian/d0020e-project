package org.example;

import org.example.repositories.InstructionRepository;
import org.example.models.RecycleInstructionsEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class TestData implements ApplicationRunner {


//    @Autowired
    private InstructionRepository instructionRepository;

    public TestData(InstructionRepository instructionRepository) {
        this.instructionRepository = instructionRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Inserting Testing Data");

        // Insert testing data
        RecycleInstructionsEntry entry = new RecycleInstructionsEntry("iron");
        entry.addInstructions("Melt it down.");
        instructionRepository.save(entry);

        System.out.println("Done with testing data");
    }
}
