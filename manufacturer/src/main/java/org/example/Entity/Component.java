package org.example.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Component {

    @Id
    private int PC_ID;

    private String typeOfComponent; //gpu, cpu, hdd, sdd, etc..
    private String modelNumber;
    private String batchNumber;

    public void setPC_ID(int ID){
        this.PC_ID = ID;
    }

    public int getPC_ID(){
        return this.PC_ID;                       //return pc for this ID
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public void setComponentID(int componentID) {
        this.PC_ID = componentID;
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

    public int getComponentID() {
        return PC_ID;
    }
}
