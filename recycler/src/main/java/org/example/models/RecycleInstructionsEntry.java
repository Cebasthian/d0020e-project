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

    private String material_id;

    @ElementCollection
    private List<String> instructions;

    private RecycleInstructionsEntry() {}



    public RecycleInstructionsEntry(String material_id) {
        this.material_id = material_id;
        this.instructions = new ArrayList<>();
    }

    public Integer getId() {
        return this.id;
    }

    public String getMaterialId() {
        return this.material_id;
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
