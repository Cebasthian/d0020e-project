package org.example.services;

import org.example.exceptions.NoSuchEntryException;
import org.example.models.RecycleInstructionsEntry;
import org.example.repositories.InstructionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructionService {

    @Autowired
    private InstructionRepository instructionRepository;

    public Iterable<RecycleInstructionsEntry> getAllEntries() {
        return instructionRepository.findAll();
    }

    public RecycleInstructionsEntry createInstructionsEntry(String materialId) {
        return instructionRepository.save(new RecycleInstructionsEntry(materialId));
    }

    public RecycleInstructionsEntry getInstructionsEntry(String materialId) {
        return instructionRepository.selectByMaterial(materialId);
    }

    public void updateInstructionsEntry(RecycleInstructionsEntry entry) throws NoSuchEntryException {
        if(instructionRepository.existsById(entry.getId())) {
            instructionRepository.save(entry);
        } else {
            throw new NoSuchEntryException();
        }
    }

    public void deleteInstructionsEntry(Integer id) {
        instructionRepository.deleteById(id);
    }

    public void addInstruction(String materialId, String instruction) {
        // EV. byta ut det här mot en custom SQL query.
        RecycleInstructionsEntry entry = instructionRepository.selectByMaterial(materialId);
        entry.addInstructions(instruction);
        instructionRepository.save(entry);
    }

    public void removeInstruction(String materialId, String instruction) {
        RecycleInstructionsEntry entry = instructionRepository.selectByMaterial(materialId);
        entry.removeInstruction(instruction);
        instructionRepository.save(entry);
    }

    public void reorderInstructions(RecycleInstructionsEntry entry) {
        updateInstructionsEntry(entry);
    }
}
