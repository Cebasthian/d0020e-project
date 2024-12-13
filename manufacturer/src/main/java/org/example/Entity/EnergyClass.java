package org.example.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class EnergyClass {

    @Id
    private int PC_ID;
    private String energyClass;

    public void setPC_ID(int id){
        this.PC_ID = id;
    }

    public int getPC_ID(){
        return PC_ID;
    }

    public void set_energyClass(String Class){
        this.energyClass = Class;
    }

    public String get_energyClass(){
        return energyClass;
    }


}
