package org.example.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

@Repository
public interface ComponentRepository extends JpaRepository<Component, Integer> {

   @GetMapping("/findComponent")
   // Returnera component baserad på ID (PC)
    public default Long findComponent(int ID){
        Long x = 123L;
        return x;
    }

    @GetMapping("/saveComponent")
    // Spara ned component
    public default void saveComponent(){
    }
}
