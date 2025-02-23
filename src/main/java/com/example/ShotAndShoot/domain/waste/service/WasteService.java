package com.example.ShotAndShoot.domain.waste.service;

import com.example.ShotAndShoot.domain.waste.dto.WasteResponseDTO;
import com.example.ShotAndShoot.domain.waste.repository.WasteRepository;
import com.example.ShotAndShoot.global.entity.Waste;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WasteService {

    private final WasteRepository wasteRepository;

    @Transactional(readOnly = true)
    public List<WasteResponseDTO> getAllWaste() {
        return wasteRepository.findAll().stream()
                .map(WasteResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public Waste getSortedWaste(String wasteName) {
        return wasteRepository.findByWasteName(wasteName).orElseThrow(
                () -> new IllegalArgumentException("해당되는 폐기물 정보가 없습니다.")
        );
    }
}
