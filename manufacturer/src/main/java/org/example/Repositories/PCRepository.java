package org.example.Repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import org.example.Entity.PC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static jakarta.persistence.LockModeType.*;

@Repository
public interface PCRepository extends JpaRepository<PC, Long> {

    // Hittar motsvarande dator på ID
    //public default PC findbyID(int ID){
      //  return new PC();
    //}

    // Lägga in alla värden för PC i databas
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    PC saveAndFlush(PC PC);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<PC> findByID(Long ID);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    void deleteById (Long ID);

    //@Lock(LockModeType.PESSIMISTIC_WRITE)
    //void updateById(Long ID);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<PC> findAll();

    // Ta bort en PC
    //public default void deletebyID(int ID){
        //hitta dator med input id och ta bort från lista

    //}
    public default void change(Long ID){

    }

    //SQL query som ska hämta ut alla PCs
    //public default List<PC> findAll(){
      //  List<PC> PCs = new ArrayList<>();
        //return PCs;
    //}
}
