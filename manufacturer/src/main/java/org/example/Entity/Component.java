package org.example.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Component {

    // snacka med Sebastian & Tom om det här behövs. De hade något liknande i sin entity.Materials i Supplier. (där de använder @ManyToOne)
    @Id
    private int PC_ID;

    // Saknas ett ID field
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;

    private String componentType; //gpu, cpu, hdd, sdd, etc..
    private String componentId;
    private String name;

    public void setPC_ID(int ID){
        this.PC_ID = ID;
    }

    public int getPC_ID(){
        return this.PC_ID;                       //return pc for this ID
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
