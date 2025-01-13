package org.example.repository;

import org.example.entity.GPU;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GpuRepository extends JpaRepository<GPU, Integer> {
}
