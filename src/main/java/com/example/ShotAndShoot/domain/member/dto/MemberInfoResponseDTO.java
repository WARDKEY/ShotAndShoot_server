package com.example.ShotAndShoot.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfoResponseDTO {
    private String nickName;
    private String address;
    private String detailAddress;
}
