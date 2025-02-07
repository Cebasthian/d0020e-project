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
    @Schema(requiredMode = RequiredMode.REQUIRED)
    private Long ID;

    private String productId;

    @NotNull
    @OneToMany(mappedBy = "PC", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @Schema(requiredMode = RequiredMode.REQUIRED)
    private List<Component> components = new ArrayList<>();

    @Schema(requiredMode = RequiredMode.REQUIRED)
    private String energyClass;

    @Schema(requiredMode = RequiredMode.REQUIRED)
    private String dimensions; // kanske är rimligare med String av typen '20x40x10 cm'

    private String lifecycle;

    @Schema(requiredMode = RequiredMode.REQUIRED)
    private int powerRating;

    private String installingInstructions;
    private String maintenanceInstructions;
    private String repairInstructions;
    private String assemblyCarbonFootprint;

    @Schema(requiredMode = RequiredMode.REQUIRED)
    private String warranty;

    @Schema(hidden = true)
    public Long getPC_ID(){
        return this.ID;                       //return pc ID
    }

    public void setProductId(String ID){
        this.productId = ID;
    }

    public String getProductId(){
        return this.productId;                       //return dpp id
    }

    public void add_component(Component Component){
        this.components.add(Component);
    }

    @Schema(hidden = true)
    public List<Component> getComponents(){
        return this.components;
    }

    public void set_energyClass(String Class){
        this.energyClass = Class;
    }

    @Schema(hidden = true)
    public String get_energyClass(){
        return this.energyClass;
    }

    @Schema(hidden = true)
    public void set_dimensions(String Dimensions){
        this.dimensions = Dimensions;
    }

    @Schema(hidden = true)
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

    @Schema(hidden = true)
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

    @Schema(hidden = true)
    public String get_warranty(){
        return this.warranty;
    }
}