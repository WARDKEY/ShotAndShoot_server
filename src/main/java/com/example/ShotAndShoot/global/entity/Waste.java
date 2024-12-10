package com.example.ShotAndShoot.global.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "waste")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Waste {

    @Id
    @Column(name = "waste_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long wasteId;

    @Column(name = "waste_name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String wasteName;

    @Column(name= "waste_sorting_info", nullable = false, columnDefinition = "TEXT")
    private String wasteSortingInfo;

    @Builder Waste(String wasteName, String wasteSortingInfo) {
        this.wasteName = wasteName;
        this.wasteSortingInfo = wasteSortingInfo;
    }
}
