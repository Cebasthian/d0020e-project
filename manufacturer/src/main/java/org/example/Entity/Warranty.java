package org.example.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Warranty {

    @Id
    private int PC_ID;
    private int warranty;

    public void setPC_ID(int id){
        this.PC_ID = id;
    }

    public int getPC_ID(){
        return PC_ID;
    }

    public void set_warranty(int warnt){
        this.warranty = warnt;
    }

    public int get_warranty(){
        return this.warranty;
    }
}
