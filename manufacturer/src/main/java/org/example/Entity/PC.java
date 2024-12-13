package org.example.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PC {

    @Id
    private int PC_ID;

    private Long component;
    private String energyClass;
    private int performance;
    private int size;
    private int warranty;

    public void setPC_ID(int ID){
        this.PC_ID = ID;
    }

    public int getPC_ID(){
        return this.PC_ID;                       //return pc for this ID
    }

    public void set_component(Long Component){
        this.component = Component;
    }

    public Long get_component(){
        return this.component;
    }

    public void set_energyClass(String Class){
        this.energyClass = Class;
    }

    public String get_energyClass(){
        return this.energyClass;
    }

    public void set_performance(int Perf){
        this.performance = Perf;
    }

    public int get_performance(){
        return performance;
    }

    public void set_size(int Size){
        this.size = Size;
    }

    public int get_size(){
        return this.size;
    }

    public void set_warranty(int warnt){
        this.warranty = warnt;
    }

    public int get_warranty(){
        return this.warranty;
    }
}