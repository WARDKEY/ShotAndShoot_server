package com.example.ShotAndShoot.domain.waste.repository;

import com.example.ShotAndShoot.global.entity.Waste;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WasteRepository extends JpaRepository<Waste, Long> {
    Optional<Waste> findByWasteName(String wasteName);

}
