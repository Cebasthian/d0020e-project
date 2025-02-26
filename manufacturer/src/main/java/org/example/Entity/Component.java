package org.example.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "components")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "pc_id")
    @JsonBackReference
    @Schema(hidden = true)
    private PC pc;

    private String componentType; //gpu, cpu, hdd, sdd, etc..
    private String componentId;
    private String name;

    //public void setID(int ID){
        //this.ID = ID;
    //}

    public int getID(){
        return this.id;                       //return pc for this ID
    }

    public String getComponentType() {
        return this.componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public String getComponentId() {
        return this.componentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setPc(PC pc) {
        this.pc = pc;
    }

}
