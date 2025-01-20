package org.example.Repositories;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Repository
public interface ComponentRepository extends JpaRepository<Component, Integer> {

    // JpaRepository har redan metoder för find och save
    // det skulle alltså funka att ha hela klassen tom så länge den extendar JpaRepository

   // Returnera component baserad på ID (PC)
    public default Long findComponent(int ID){
        Long x = 123L;
        return x;
    }


    // Spara ned component
    public default void saveComponent(){
    }
}