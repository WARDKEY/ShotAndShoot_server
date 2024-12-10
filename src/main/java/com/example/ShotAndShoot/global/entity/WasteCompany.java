package com.example.ShotAndShoot.global.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "waste_company")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WasteCompany {

    @Id
    @Column(name = "waste_company_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long wasteCompanyId;

    @Column(name = "waste_company_name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String wasteCompanyName;

    @Column(name = "phone_number", nullable = false, columnDefinition = "VARCHAR(25)")
    private String phoneNumber;

    @Builder WasteCompany(String wasteCompanyName, String phoneNumber) {
        this.wasteCompanyName = wasteCompanyName;
        this.phoneNumber = phoneNumber;
    }
}
