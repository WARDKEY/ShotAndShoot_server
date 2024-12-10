package com.example.ShotAndShoot.domain.wasteCompany.dto;

import com.example.ShotAndShoot.global.entity.WasteCompany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WasteCompanyResponseDTO {
    private Long wasteCompanyId;
    private String wasteCompanyName;
    private String phoneNumber;

    public WasteCompanyResponseDTO(WasteCompany wasteCompany) {
        this.wasteCompanyId = wasteCompany.getWasteCompanyId();
        this.wasteCompanyName = wasteCompany.getWasteCompanyName();
        this.phoneNumber = wasteCompany.getPhoneNumber();

    }
}
