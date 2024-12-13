package org.example.repository;

import org.example.entity.Materials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Materials, Integer> {
}
