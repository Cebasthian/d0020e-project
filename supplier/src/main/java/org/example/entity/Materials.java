package org.example.entity;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "materials")
public class Materials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String materialType;

    private String origin;
    private String materialFlow;
    private String supplier;


    @ManyToMany(mappedBy = "materials")
    @Schema(hidden = true)
    @JsonIgnore
    private List<GPU> gpus = new ArrayList<>();

    public List<GPU> getGpus() { return gpus;}
    public void setGpus(List<GPU> gpus) {
        this.gpus = gpus;
    }




    // Setters: ##################################################################

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setMaterialFlow(String materialFlow) {
        this.materialFlow = materialFlow;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    // Getters: ##################################################################

    public Integer getId() {
        return id;
    }

    public String getMaterialType() {
        return materialType;
    }

    public String getOrigin() {
        return origin;
    }

    public String getMaterialFlow() {
        return materialFlow;
    }

    public String getSupplier() {
        return supplier;
    }

}
