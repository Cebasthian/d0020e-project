package org.example.Repositories;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

@Repository
public class PCRepository {
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
