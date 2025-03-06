package org.example.repository;

import org.example.entity.GPU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GpuRepository extends JpaRepository<GPU, Integer> {
    @Query("SELECT g FROM GPU g LEFT JOIN FETCH g.materials WHERE g.id = :id") // troligen overkill men lämnar kvar det (nöjd med att det funkar lmao)
    Optional<GPU> findByIdWithMaterials(Integer id);
}
