package com.example.ShotAndShoot.domain.wasteCompany.dto;

import com.example.ShotAndShoot.global.entity.WasteCompany;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
public class WasteCompanyResponseDTO {

    private List<WasteCompanyDTO> wasteCompany;

    public WasteCompanyResponseDTO(List<WasteCompanyDTO> wasteCompany) {
        this.wasteCompany = wasteCompany;
    }

    @Builder
    @Getter
    public static class WasteCompanyDTO {
        String wasteCompanyName;
        String address;
        String phoneNumber;
    }
}
