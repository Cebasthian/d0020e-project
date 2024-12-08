package org.example.Repositories;

import org.example.Entity.PC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

@Repository
public interface PCRepository extends JpaRepository<PC, String> {
    @GetMapping("/findbyID")
    public void findbyID(){

    }

    @GetMapping("/save")
    public void save(){

    }

    @GetMapping("/deletebyID")
    public void deletebyID(){

    }
}
