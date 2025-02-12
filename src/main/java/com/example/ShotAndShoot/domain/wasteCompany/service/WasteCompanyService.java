package com.example.ShotAndShoot.domain.wasteCompany.service;

import com.example.ShotAndShoot.domain.member.repository.MemberRepository;
import com.example.ShotAndShoot.domain.member.service.MemberService;
import com.example.ShotAndShoot.domain.wasteCompany.dto.ExtendedWasteCompanyDTO;
import com.example.ShotAndShoot.domain.wasteCompany.dto.WasteCompanyResponseDTO;
import com.example.ShotAndShoot.domain.wasteCompany.dto.WasteCompanyResponseDTO.WasteCompanyDTO;
import com.example.ShotAndShoot.global.entity.Member;
import io.micrometer.common.lang.Nullable;
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
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class WasteCompanyService {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    Map<String, Integer> dataMap = new HashMap<String, Integer>() {
        {
            put("서울특별시 중구", 3010000);
            put("서울특별시 용산구", 3020000);
            put("서울특별시 노원구", 3100000);
            put("서울특별시 강서구", 3150000);
            put("서울특별시 관악구", 3200000);
            put("서울특별시 서초구", 3210000);
            put("서울특별시 강남구", 3220000);
            put("부산광역시 서구", 3260000);
            put("부산광역시 영도구", 3280000);
            put("부산광역시 부산진구", 3290000);
            put("부산광역시 동래구", 3300000);
            put("부산광역시 남구", 3310000);
            put("부산광역시 북구", 3320000);
            put("부산광역시 해운대구", 3330000);
            put("부산광역시 금정구", 3350000);
            put("부산광역시 강서구", 3360000);
            put("부산광역시 연제구", 3370000);
            put("부산광역시 수영구", 3380000);
            put("부산광역시 사상구", 3390000);
            put("대구광역시 중구", 3410000);
            put("대구광역시 남구", 3440000);
            put("대구광역시 북구", 3450000);
            put("대구광역시 수성구", 3460000);
            put("대구광역시 달서구", 3470000);
            put("인천광역시 남동구", 3530000);
            put("인천광역시 계양구", 3550000);
            put("인천광역시 강화군", 3570000);
            put("광주광역시 서구", 3600000);
            put("광주광역시 광산구", 3630000);
            put("대전광역시 서구", 3660000);
            put("대전광역시 유성구", 3670000);
            put("울산광역시 남구", 3700000);
            put("경기도 수원시", 3740000);
            put("경기도 의정부시", 3820000);
            put("경기도 평택시", 3910000);
            put("경기도 안산시", 3930000);
            put("경기도 남양주시", 3990000);
            put("경기도 오산시", 4000000);
            put("경기도 용인시", 4050000);
            put("경기도 파주시", 4060000);
            put("경기도 이천시", 4070000);
            put("경기도 안성시", 4080000);
            put("경기도 가평군", 4160000);
            put("강원특별자치도 강릉시", 4201000);
            put("강원특별자치도 동해시", 4211000);
            put("강원특별자치도 태백시", 4221000);
            put("강원특별자치도 삼척시", 4241000);
            put("강원특별자치도 홍천군", 4251000);
            put("강원특별자치도 화천군", 4311000);
            put("강원특별자치도 양양군", 4351000);
            put("충청북도 충주시", 4390000);
            put("충청북도 옥천군", 4430000);
            put("충청북도 진천군", 4450000);
            put("충청북도 단양군", 4480000);
            put("충청남도 서산시", 4530000);
            put("충청남도 금산군", 4550000);
            put("충청남도 부여군", 4570000);
            put("충청남도 홍성군", 4600000);
            put("충청남도 예산군", 4610000);
            put("전북특별자치도 군산시", 4671000);
            put("전북특별자치도 익산시", 4681000);
            put("전북특별자치도 완주군", 4721000);
            put("전북특별자치도 진안군", 4731000);
            put("전북특별자치도 임실군", 4761000);
            put("전북특별자치도 고창군", 4781000);
            put("전라남도 여수시", 4810000);
            put("전라남도 순천시", 4820000);
            put("전라남도 구례군", 4870000);
            put("전라남도 보성군", 4890000);
            put("전라남도 화순군", 4900000);
            put("전라남도 장흥군", 4910000);
            put("전라남도 강진군", 4920000);
            put("전라남도 영광군", 4970000);
            put("전라남도 완도군", 4990000);
            put("전라남도 신안군", 5010000);
            put("경상북도 안동시", 5070000);
            put("경상북도 영주시", 5090000);
            put("경상북도 영천시", 5100000);
            put("경상북도 경산시", 5130000);
            put("경상북도 청송군", 5160000);
            put("경상북도 영덕군", 5180000);
            put("경상북도 청도군", 5190000);
            put("경상북도 성주군", 5210000);
            put("경상남도 진주시", 5310000);
            put("경상남도 사천시", 5340000);
            put("경상남도 하동군", 5440000);
            put("경상남도 함양군", 5460000);
            put("경상남도 거창군", 5470000);
            put("경상남도 합천군", 5480000);
            put("경기도 화성시", 5530000);
            put("충청북도 증평군", 5570000);
            put("경기도 양주시", 5590000);
            put("경상남도 창원시", 5670000);
            put("경기도 여주시", 5700000);
            put("충청북도 청주시", 5710000);
            put("제주특별자치도 서귀포시", 6520000);
        }
    };

    @Value("${API_KEY}")
    private String apiKey;

    @Transactional(readOnly = true)
    public WasteCompanyResponseDTO getAllWasteCompany(@Nullable String location) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        int code;

        if (location == null || location.isEmpty()) {
            code = 3010000; // 기본 코드
        } else {
            code = dataMap.keySet().stream()
                    .filter(location::contains)
                    .findFirst()
                    .map(dataMap::get)
                    .orElse(3010000); // 기본 코드
        }

        String url = String.format("http://api.data.go.kr/openapi/tn_pubr_public_tret_was_api?instt_code=%d&type=json&serviceKey=%s", code, apiKey);
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

        for (Map<String, Object> wasteCompany : wasteCompanyItems) {
            String wasteCompanyName = (String) wasteCompany.get("flctNm");
            String address = (String) wasteCompany.get("lctnRoadNmAddr");
            String phoneNumber = (String) wasteCompany.get("telno");
            String lat = (String) wasteCompany.get("lat");
            String lot = (String) wasteCompany.get("lot");

            WasteCompanyDTO wasteCompanyDTO = WasteCompanyDTO.builder()
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

    @Transactional(readOnly = true)
    public ExtendedWasteCompanyDTO getWasteCompany() throws URISyntaxException {
        Optional<Member> member = memberRepository.findById(memberService.getLoginMemberId());
        RestTemplate restTemplate = new RestTemplate();

        String myAddr = "서울특별시 중구";
        int code;

        if(member.isPresent()) {
            myAddr = member.get().getAddress();
            code = dataMap.keySet().stream()
                    .filter(myAddr::contains)
                    .findFirst()
                    .map(dataMap::get)
                    .orElse(3010000);
        } else {
            code = 3010000;
        }

        String url = String.format("http://api.data.go.kr/openapi/tn_pubr_public_tret_was_api?numOfRows=1&instt_code=%d&type=json&serviceKey=%s", code, apiKey);
        URI uri = new URI(url);
        HttpEntity<?> entity = new HttpEntity<>(new HttpHeaders());

        ResponseEntity<Map> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                entity,
                Map.class
        );

        Map<String, Object> wasteCompanyResponse = (Map<String, Object>) response.getBody().get("response");
        Map<String, Object> wasteCompanyBody = (Map<String, Object>) wasteCompanyResponse.get("body");
        List<Map<String, Object>> wasteCompanyItems = (List<Map<String, Object>>) wasteCompanyBody.get("items");
        WasteCompanyDTO wasteCompanyDTO = null;

        for (Map<String, Object> wasteCompany : wasteCompanyItems) {
            String wasteCompanyName = (String) wasteCompany.get("flctNm");
            String address = (String) wasteCompany.get("lctnRoadNmAddr");
            String phoneNumber = (String) wasteCompany.get("telno");
            String lat = (String) wasteCompany.get("lat");
            String lot = (String) wasteCompany.get("lot");

            wasteCompanyDTO = WasteCompanyDTO.builder()
                    .wasteCompanyName(wasteCompanyName)
                    .address(address)
                    .phoneNumber(phoneNumber)
                    .lat(lat)
                    .lot(lot)
                    .build();

        }
        return new ExtendedWasteCompanyDTO(wasteCompanyDTO, myAddr);
    }
}
