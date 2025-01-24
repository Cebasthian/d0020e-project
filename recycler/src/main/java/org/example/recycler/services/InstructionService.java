package org.example.recycler.services;

import org.example.recycler.exceptions.EntryAlreadyExistsException;
import org.example.recycler.exceptions.NoSuchEntryException;
import org.example.recycler.models.RecycleInstructionsEntry;
import org.example.recycler.repositories.InstructionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructionService {

    @Autowired
    private InstructionRepository instructionRepository;

    public List<RecycleInstructionsEntry> getAllEntries() {
        return instructionRepository.findAll();
    }

    public RecycleInstructionsEntry createInstructionsEntry(String materialId) throws EntryAlreadyExistsException {
        if(instructionRepository.existsByMaterial(materialId)) {
            throw new EntryAlreadyExistsException();
        } else {
            return instructionRepository.saveAndFlush(new RecycleInstructionsEntry(materialId));
        }
    }

    public RecycleInstructionsEntry getInstructionsEntry(String materialId) throws NoSuchEntryException {
        if(instructionRepository.existsByMaterial(materialId)) {
            return instructionRepository.selectByMaterial(materialId);
        } else {
            throw new NoSuchEntryException();
        }
    }

    public RecycleInstructionsEntry updateInstructionsEntry(RecycleInstructionsEntry entry) throws NoSuchEntryException {
        if(instructionRepository.existsById(entry.getId())) {
            return instructionRepository.saveAndFlush(entry);
        } else {
            throw new NoSuchEntryException();
        }
    }

    public void deleteInstructionsEntry(Integer id) throws NoSuchEntryException {
        if(instructionRepository.existsById(id)) {
            instructionRepository.deleteById(id);
        } else {
            throw new NoSuchEntryException();
        }
    }


    // TODO: remove these unused methods
//    public void addInstruction(String materialId, String instruction) {
//        // EV. byta ut det här mot en custom SQL query.
//        RecycleInstructionsEntry entry = instructionRepository.selectByMaterial(materialId);
//        entry.addInstructions(instruction);
//        instructionRepository.save(entry);
//    }
//
//    public void removeInstruction(String materialId, String instruction) {
//        RecycleInstructionsEntry entry = instructionRepository.selectByMaterial(materialId);
//        entry.removeInstruction(instruction);
//        instructionRepository.save(entry);
//    }
//
//    public void reorderInstructions(RecycleInstructionsEntry entry) {
//        updateInstructionsEntry(entry);
//    }
}
