package com.example.ShotAndShoot.domain.wasteCompany.service;

import com.example.ShotAndShoot.domain.wasteCompany.dto.WasteCompanyResponseDTO;
import com.example.ShotAndShoot.domain.wasteCompany.repository.WasteCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WasteCompanyService {

    private final WasteCompanyRepository wasteCompanyRepository;

    @Transactional(readOnly = true)
    public List<WasteCompanyResponseDTO> getAllWasteCompany() {
        return wasteCompanyRepository.findAll().stream()
                .map(WasteCompanyResponseDTO::new)
                .toList();
    }
}
