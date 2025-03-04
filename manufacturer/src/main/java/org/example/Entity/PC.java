package org.example.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import io.swagger.v3.oas.annotations.media.Schema;
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
    private Long id;

    private String name;

    private String productId;

    //@NotNull
    @OneToMany(mappedBy = "pc", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Component> components = new ArrayList<>();

    private String energyClass;

    private String dimension; // kanske är rimligare med String av typen '20x40x10 cm'

    private String lifecycle;

    private int powerRating;

    private String installingInstructions;
    private String maintenanceInstructions;
    private String repairInstructions;
    private String assemblyCarbonFootprint;

    private String warranty;

    public String getName(){
        return this.name;
    }

    public void setName(String Name){
        this.name = Name;
    }

    //@Schema(hidden = true)
    public Long getID(){
        return this.id;                       //return pc ID
    }

    public void setProductId(String ID){
        this.productId = ID;
    }

    public String getProductId(){
        return this.productId;                       //return dpp id
    }

    public void addComponent(Component component){
        component.setPc(this);
        this.components.add(component);
    }

    public void setComponent(int id, String newComponentType, String newComponentId, String newComponentName) {
        if(id != 0){
            for (Component component : components) {
                if (component.getID() == id) {
                    if(newComponentType != null){component.setComponentType(newComponentType);}
                    if(newComponentId != null){component.setComponentId(newComponentId);}
                    if(newComponentName != null){component.setName(newComponentName);}

                    break; // Exit loop once found
                }
            }
        }
    }

    //@Schema(hidden = true)
    public List<Component> getComponents(){
        return this.components;
    }

    public void setEnergyClass(String Class){
        this.energyClass = Class;
    }

    //@Schema(hidden = true)
    public String getEnergyClass(){
        return this.energyClass;
    }

    //@Schema(hidden = true)
    public void setDimension(String Dimension){
        this.dimension = Dimension;
    }

    //@Schema(hidden = true)
    public String getDimension(){
        return this.dimension;
    }

    public void setLifecycle(String lifecycle){
        this.lifecycle = lifecycle;
    }

    public String getLifecycle(){
        return this.lifecycle;
    }

    public void setPowerRating(int powerRating){
        this.powerRating = powerRating;
    }

    //@Schema(hidden = true)
    public int getPowerRating(){
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

    public void setWarranty(String warnt){
        this.warranty = warnt;
    }

    //@Schema(hidden = true)
    public String getWarranty(){
        return this.warranty;
    }
}