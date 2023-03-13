package com.example.repository;

import com.example.entity.GeoJsonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeoJsonRepository extends JpaRepository<GeoJsonEntity, Long> {
}
