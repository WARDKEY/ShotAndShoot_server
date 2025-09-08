package com.example.ShotAndShoot.domain.wasteCompany.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExtendedWasteCompanyDTO {
    private WasteCompanyResponseDTO.WasteCompanyDTO wasteCompanyDTO;
    private String myAddress;

    // 생성자
    public ExtendedWasteCompanyDTO(WasteCompanyResponseDTO.WasteCompanyDTO wasteCompanyDTO, String myAddress) {
        this.wasteCompanyDTO = wasteCompanyDTO;
        this.myAddress = myAddress;
    }
}

