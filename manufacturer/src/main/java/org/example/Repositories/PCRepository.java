package org.example.Repositories;

import org.example.Entity.PC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Repository
public interface PCRepository extends JpaRepository<PC, String> {

    @GetMapping("/findbyID")
    // Hittar motsvarande dator på ID
    public default PC findbyID(int ID){
        return new PC();
    }

    // Lägga in PC i lista/databas
    @GetMapping("/save")
    public default void save(){

    }

    // Ta bort en PC
    @GetMapping("/deletebyID")
    public default void deletebyID(int ID){

    }
}
