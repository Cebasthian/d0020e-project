package org.example.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "components")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @ManyToOne
    @JoinColumn(name = "PC")
    private PC PC;

    private String componentType; //gpu, cpu, hdd, sdd, etc..
    private String componentId;
    private String name;

    public void setPC_ID(int ID){
        this.ID = ID;
    }

    public int getPC_ID(){
        return this.ID;                       //return pc for this ID
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

}
