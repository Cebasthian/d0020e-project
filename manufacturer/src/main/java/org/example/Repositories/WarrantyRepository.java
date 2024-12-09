package org.example.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

@Repository
public interface WarrantyRepository extends JpaRepository<Warranty, Integer> {

    @GetMapping("/findbyID")
    public int findbyID(int ID){

    }

    @GetMapping("/save")
    public void save(){

    }
}
