package org.example.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table
public class RecycleInstructionsEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String materialId;

    @ElementCollection
    private List<String> instructions;

    private RecycleInstructionsEntry() {}



    public RecycleInstructionsEntry(String materialId) {
        this.materialId = materialId;
        this.instructions = new ArrayList<>();
    }

    public Integer getId() {
        return this.id;
    }

    public String getMaterialId() {
        return this.materialId;
    }

    public List<String> getInstructions() {
        return this.instructions;
    }

    public boolean addInstructions(String instruction) {
        return this.instructions.add(instruction);
    }

    public boolean addInstructions(Collection<String> instructions) {
        return this.instructions.addAll(instructions);
    }

    public boolean removeInstruction(String instruction) {
        return this.instructions.remove(instruction);
    }
}
