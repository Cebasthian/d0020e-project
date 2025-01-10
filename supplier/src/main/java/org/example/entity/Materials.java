package org.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "materials")
public class Materials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String materialType;

    private String origin; // Country of origin
    private String materialFlow; // Hur materialet rör sig i supply kedjan?
    private String supplier;


    //Behöver testas om detta behövs.

    @ManyToOne
    @JoinColumn(name = "gpu_id", nullable = false)
    @JsonBackReference
    @Schema(hidden=true)
    private GPU gpu;

    public GPU getGpu() { return gpu;}
    public void setGpu(GPU gpu) { this.gpu = gpu; }




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

    public int getId() {
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
