package org.example.Repositories;

import org.example.Entity.EnergyClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

@Repository
public interface EnergyclassRepository extends JpaRepository<EnergyClass, String> {

   @GetMapping("/findbyID")
    public String findbyID(int ID){
    }

    @GetMapping("/save")
    public void save(){

    }
}
