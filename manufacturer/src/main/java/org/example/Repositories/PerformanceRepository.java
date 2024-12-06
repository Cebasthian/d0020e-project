package org.example.Repositories;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

@Repository
public class PerformanceRepository {

    @GetMapping("/findbyID")
    public void findbyID(){

    }

    @GetMapping("/save")
    public void save(){

    }
}
