package org.example.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PCs")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    private String productId;

    @NotNull
    @OneToMany(mappedBy = "PC", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Component> components = new ArrayList<>();

    private String energyClass;
    private String dimensions; // kanske är rimligare med String av typen '20x40x10 cm'
    private String lifecycle;
    private int powerRating;
    private String installingInstructions;
    private String maintenanceInstructions;
    private String repairInstructions;
    private String assemblyCarbonFootprint;
    private String warranty;

    public int getPC_ID(){
        return this.ID;                       //return pc ID
    }

    public void setProductId(String ID){
        this.productId = String.valueOf(ID);
    }

    public String getProductId(){
        return this.productId;                       //return dpp id
    }

    public void add_component(Component Component){
        this.components.add(Component);
    }

    public List<Component> get_components(){
        return this.components;
    }

    public void set_energyClass(String Class){
        this.energyClass = Class;
    }

    public String get_energyClass(){
        return this.energyClass;
    }

    public void set_dimensions(String Dimensions){
        this.dimensions = Dimensions;
    }

    public String get_size(){
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