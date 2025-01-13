package org.example.Entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.ArrayList;

@Entity
public class PC {

    @Id
    private int PC_ID;

    private String productId;

    @ElementCollection
    private ArrayList<Integer> components;            //ska vara array av components

    private String energyClass;
    private int dimensions;
    private String lifecycle;
    private int powerRating;
    private String installingInstructions;
    private String maintenanceInstructions;
    private String repairInstructions;
    private String assemblyCarbonFootprint;
    private String warranty;

    public void setPC_ID(int ID){
        this.PC_ID = ID;
    }

    public int getPC_ID(){
        return this.PC_ID;                       //return pc ID
    }

    public void setProductId(int ID){
        this.productId = String.valueOf(ID);
    }

    public String getProductId(){
        return this.productId;                       //return dpp id
    }

    public void add_component(Long Component){
        //this.components = Component;          //måste loopa in på ledig plats
    }

    public ArrayList<Integer> get_components(){
        return this.components;
    }

    public void set_energyClass(String Class){
        this.energyClass = Class;
    }

    public String get_energyClass(){
        return this.energyClass;
    }

    public void set_dimensions(int Dimensions){
        this.dimensions = Dimensions;
    }

    public int get_size(){
        return this.dimensions;
    }

    public void setLifecycle(String lifecycle){
        this.lifecycle = lifecycle;
    }

    public String getLifecycle(){
        return this.lifecycle;
    }

    public void set_powerRating(int powerRating){
        this.powerRating = powerRating;
    }

    public int get_powerRating(){
        return this.powerRating;
    }

    public void setInstallingInstructions(String installingInstructions){
        this.installingInstructions = installingInstructions;
    }

    public String getInstallingInstructions(){
        return this.installingInstructions;
    }

    public void setMaintenanceInstructions(String maintenanceInstructions){
        this.maintenanceInstructions = maintenanceInstructions;
    }

    public String getMaintenanceInstructions(){
        return this.maintenanceInstructions;
    }

    public void setRepairInstructions(String repairInstructions){
        this.repairInstructions = repairInstructions;
    }

    public String getRepairInstructions(){
        return this.repairInstructions;
    }

    public void setAssemblyCarbonFootprint(String assemblyCarbonFootprint){
        this.assemblyCarbonFootprint = assemblyCarbonFootprint;
    }

    public String getAssemblyCarbonFootprint(){
        return this.assemblyCarbonFootprint;
    }

    public void set_warranty(String warnt){
        this.warranty = warnt;
    }

    public String get_warranty(){
        return this.warranty;
    }
}