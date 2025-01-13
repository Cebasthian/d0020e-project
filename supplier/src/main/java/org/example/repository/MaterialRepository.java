package org.example.repository;

import org.example.entity.Materials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MaterialRepository extends JpaRepository<Materials, Integer> {
    Optional<Materials> findByMaterialType(String materialType);
}
