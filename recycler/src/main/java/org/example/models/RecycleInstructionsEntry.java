package org.example.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table
public class RecycleInstructionsEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Integer id;

    @NotNull
    @Column(unique = true)
    private String materialId;

    @NotNull
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
