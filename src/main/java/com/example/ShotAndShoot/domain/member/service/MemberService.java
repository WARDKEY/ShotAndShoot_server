package com.example.ShotAndShoot.domain.member.service;

import com.example.ShotAndShoot.domain.member.dto.MemberRequestDTO;
import com.example.ShotAndShoot.domain.member.dto.MemberResponseDTO;
import com.example.ShotAndShoot.domain.member.repsitory.MemberRepository;
import com.example.ShotAndShoot.global.entity.Member;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public String register(MemberRequestDTO memberRequestDTO) {
        if (memberRepository.findByName(memberRequestDTO.getName()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }

        Member member = Member.builder()
                .name(memberRequestDTO.getName())
                .address(memberRequestDTO.getAddress())
                .phoneNumber(memberRequestDTO.getPhoneNumber())
                .build();
        memberRepository.save(member);

        return member.getName() + "님 회원가입이 완료되었습니다.";

    }

    public String unregister(Long memberId) {
        if (memberRepository.findById(memberId).isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }

        memberRepository.deleteById(memberId);

        return "회원탈퇴가 완료되었습니다.";
    }

    public List<MemberResponseDTO> getAllMember() {
        return memberRepository.findAll().stream()
                .map(MemberResponseDTO::new)
                .collect(Collectors.toList());

    }
}
