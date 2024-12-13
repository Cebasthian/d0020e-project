package org.example.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Size {

    @Id
    private int PC_ID;
    private int size;

    public void setPC_ID(int id){
        this.PC_ID = id;
    }

    public int getPC_ID(){
        return PC_ID;
    }

    public void set_size(int Size){
        this.size = Size;
    }

    public int get_size(){
        return this.size;
    }
}
