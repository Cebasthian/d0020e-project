package org.example.Repositories;

import org.example.Entity.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Integer> {

    @GetMapping("/findbyID")
    public int findbyID(int ID){

    }

    @GetMapping("/save")
    public void save(){

    }
}
