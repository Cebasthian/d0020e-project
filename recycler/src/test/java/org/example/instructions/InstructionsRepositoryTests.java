package org.example.instructions;

import org.example.models.RecycleInstructionsEntry;
import org.example.repositories.InstructionRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class InstructionsRepositoryTests {

    @Autowired
    private InstructionRepository instructionRepository;

    @Test
    @Order(1)
    @DisplayName("Create entry test")
    @Rollback(false)
    public void createEntryTest() {
        RecycleInstructionsEntry entry = new RecycleInstructionsEntry("air");
        entry.addInstructions("Do nothing :)");
        instructionRepository.save(entry);

        Assertions.assertTrue(entry.getId() > 0);
    }

    @Test
    @Order(2)
    @DisplayName("Entry exists test")
    public void entryExistsTest() {
        boolean exists = instructionRepository.existsByMaterial("air");
        Assertions.assertTrue(exists);
    }

    @Test
    @Order(3)
    @DisplayName("Get entry test")
    public void getEntryTest() {
        RecycleInstructionsEntry entry = instructionRepository.selectByMaterial("air");
        Assertions.assertEquals("Do nothing :)", entry.getInstructions().get(0));
    }

    @Test
    @Order(4)
    @DisplayName("Get all entries test")
    public void getEntriesTest() {
        List<RecycleInstructionsEntry> entries = instructionRepository.findAll();
        Assertions.assertFalse(entries.isEmpty());
    }

    @Test
    @Order(5)
    @DisplayName("Update entry test")
    public void updateEntryTest() {
        RecycleInstructionsEntry entry = instructionRepository.selectByMaterial("air");
        entry.addInstructions("Inhale it =.=");
        RecycleInstructionsEntry entry2 = instructionRepository.save(entry);
        Assertions.assertEquals(2, entry2.getInstructions().size());
    }

    @Test
    @Order(6)
    @DisplayName("Delete entry test")
    public void deleteEntryTest() {
        RecycleInstructionsEntry entry = instructionRepository.selectByMaterial("air");
        instructionRepository.deleteById(entry.getId());
        boolean exists = instructionRepository.existsByMaterial("air");
        Assertions.assertFalse(exists);
    }
}
