package org.example.Repositories;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

@Repository
public class SizeRepository {
    @GetMapping("/findbyID")
    public void findbyID(){

    }

    @GetMapping("/save")
    public void save(){

    }
}
