package org.example.recycler;

import org.example.recycler.repositories.InstructionRepository;
import org.example.recycler.models.RecycleInstructionsEntry;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

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
