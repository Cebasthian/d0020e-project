package org.example.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long supplierID;

    private String SupplierName;
    private float defectRate; // Vill vi ha sånt här eller?

    @OneToMany(mappedBy = "supplier",cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Component> components;

    // Getter and Setters:

    public void setSupplierID(Long supplierID) {
        this.supplierID = supplierID;
    }

    public String getSupplierName() {
        return SupplierName;
    }

    public void setSupplierName(String supplierName) {
        this.SupplierName = supplierName;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    public float getDefectRate() {
        return defectRate;
    }

    public List<Component> getComponents() {
        return components;
    }

    public Long getSupplierID() {
        return supplierID;
    }


}
