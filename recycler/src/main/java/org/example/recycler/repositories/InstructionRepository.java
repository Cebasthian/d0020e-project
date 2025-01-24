package org.example.recycler.repositories;

import jakarta.persistence.LockModeType;
import org.example.recycler.models.RecycleInstructionsEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InstructionRepository extends JpaRepository<RecycleInstructionsEntry, Integer> {

    @Query("SELECT u FROM RecycleInstructionsEntry u WHERE materialId = :materialId")
    RecycleInstructionsEntry selectByMaterial(@Param(value = "materialId") String material_id);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM RecycleInstructionsEntry c WHERE materialId = :materialId")
    boolean existsByMaterial(@Param(value = "materialId") String materialId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    RecycleInstructionsEntry saveAndFlush(RecycleInstructionsEntry entry);
}
