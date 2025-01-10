package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "gpus")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class GPU {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String componentId;

    @NotNull
    @OneToMany(mappedBy = "gpu", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Materials> materials = new ArrayList<>();

    private String name;
    private Integer weight; // grams
    private String dimensions; // "250x150x50" mm
    private Integer powerRating; // watts
    private String installingInstructions;
    private String assemblyLine; // kan diskuteras?

    // Setters: ##################################################################
    public void setId(Integer id) {
        this.id = id;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public void setMaterials(List<Materials> materials) {
        this.materials = materials;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public void setPowerRating(Integer powerRating) {
        this.powerRating = powerRating;
    }

    public void setInstallingInstructions(String installingInstructions) {
        this.installingInstructions = installingInstructions;
    }

    public void setAssemblyLine(String assemblyLine) {
        this.assemblyLine = assemblyLine;
    }

    public Integer getId() {
        return id;
    }

    public String getComponentId() {
        return componentId;
    }

    // Getters: ##################################################################

    public List<Materials> getMaterials() {
        return materials;
    }

    public String getName() {
        return name;
    }

    public Integer getWeight() {
        return weight;
    }

    public String getDimensions() {
        return dimensions;
    }

    public Integer getPowerRating() {
        return powerRating;
    }

    public String getInstallingInstructions() {
        return installingInstructions;
    }

    public String getAssemblyLine() {
        return assemblyLine;
    }


}
