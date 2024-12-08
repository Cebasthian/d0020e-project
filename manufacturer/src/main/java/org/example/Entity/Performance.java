package org.example.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Performance {

    @Id
    private int PC_ID;
    private int performance;

    public void setPC_ID(int id){
        this.PC_ID = id;
    }

    public int getPC_ID(){
        return PC_ID;
    }

    public void set_performance(int perf){
        this.performance = perf;
    }

    public int get_performance(){
        return performance;
    }
}
