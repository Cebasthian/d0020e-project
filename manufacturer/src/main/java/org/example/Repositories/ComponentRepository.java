package org.example.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

@Repository
public interface ComponentRepository extends JpaRepository<Component, Integer> {

    @GetMapping("/findbyID")
    public void findbyID(){

    }

    @GetMapping("/save")
    public void save(){

    }

}
