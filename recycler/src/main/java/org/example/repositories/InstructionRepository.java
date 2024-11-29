package org.example.repositories;

import org.example.models.RecycleInstructionsEntry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface InstructionRepository extends CrudRepository<RecycleInstructionsEntry, Integer> {

    @Query("SELECT u FROM RecycleInstructionsEntry u WHERE material_id = :material_id")
    RecycleInstructionsEntry selectByMaterial(@Param(value = "material_id") String material_id);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM RecycleInstructionsEntry c WHERE material_id = :material_id")
    boolean existsByMaterial(@Param(value = "material_id") String material_id);
}
