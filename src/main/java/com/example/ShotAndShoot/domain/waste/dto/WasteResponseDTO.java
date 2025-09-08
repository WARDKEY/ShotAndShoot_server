package com.example.ShotAndShoot.domain.waste.dto;

import com.example.ShotAndShoot.global.entity.Waste;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WasteResponseDTO {
    private Long wasteId;
    private String wasteName;
    private String wasteSortingInfo;

    public WasteResponseDTO(Waste waste) {
        this.wasteId = waste.getWasteId();
        this.wasteName = waste.getWasteName();
        this.wasteSortingInfo = waste.getWasteSortingInfo();

    }
}
