package org.example.entity;

import jakarta.persistence.*;

@Entity
public class Materials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String materialType;

    private String origin; // Country of origin
    private String materialFlow; // Hur materialet rör sig i supply kedjan?
    private String supplier;


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
