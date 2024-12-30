package com.example.ShotAndShoot.domain.wasteCompany.service;

import com.example.ShotAndShoot.domain.wasteCompany.dto.WasteCompanyResponseDTO;
import com.example.ShotAndShoot.domain.wasteCompany.dto.WasteCompanyResponseDTO.WasteCompanyDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class WasteCompanyService {

    @Value("${API_KEY}")
    private String apiKey;

    @Transactional(readOnly = true)
    public WasteCompanyResponseDTO getAllWasteCompany() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        String url = String.format("http://api.data.go.kr/openapi/tn_pubr_public_tret_was_api?type=json&serviceKey=%s", apiKey);
        URI uri = new URI(url);
        HttpEntity<?> entity = new HttpEntity<>(new HttpHeaders());

        ResponseEntity<Map> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                entity,
                Map.class
        );

        log.info(response.getBody().toString());

        Map<String, Object> wasteCompanyResponse = (Map<String, Object>) response.getBody().get("response");
        Map<String, Object> wasteCompanyBody = (Map<String, Object>) wasteCompanyResponse.get("body");
        List<Map<String, Object>> wasteCompanyItems = (List<Map<String, Object>>) wasteCompanyBody.get("items");

        List<WasteCompanyDTO> wasteCompanies = new ArrayList<>();

        for(Map<String, Object> wasteCompany : wasteCompanyItems) {
            String wasteCompanyName = (String) wasteCompany.get("flctNm");
            String address = (String) wasteCompany.get("lctnLotnoAddr");
            String phoneNumber = (String) wasteCompany.get("telno");
            String lat = (String) wasteCompany.get("lat");
            String lot = (String) wasteCompany.get("lot");

            WasteCompanyDTO wasteCompanyDTO =  WasteCompanyDTO.builder()
                    .wasteCompanyName(wasteCompanyName)
                    .address(address)
                    .phoneNumber(phoneNumber)
                    .lat(lat)
                    .lot(lot)
                    .build();

            wasteCompanies.add(wasteCompanyDTO);
        }

        return new WasteCompanyResponseDTO(wasteCompanies);
    }
}
