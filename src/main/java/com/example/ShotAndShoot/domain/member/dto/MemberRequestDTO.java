package com.example.ShotAndShoot.domain.member.dto;

import lombok.Data;

@Data
public class MemberRequestDTO {
    private String id;
    private String name;
    private String address;
    private String detailAddress;
    private String phoneNumber;
}
