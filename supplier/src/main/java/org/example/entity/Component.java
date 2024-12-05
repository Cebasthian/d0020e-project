package org.example.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long componentID;

    private String typeOfComponent; //gpu, cpu, hdd, sdd, etc..
    private String modelNumber;
    private String batchNumber;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;


    // Getters and Setters:



    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public void setComponentID(Long componentID) {
        this.componentID = componentID;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public Long getComponentID() {
        return componentID;
    }

}
