package com.example.ShotAndShoot.domain.member.controller;

import com.example.ShotAndShoot.domain.member.dto.MemberRequestDTO;
import com.example.ShotAndShoot.domain.member.dto.MemberResponseDTO;
import com.example.ShotAndShoot.domain.member.service.MemberService;
import com.example.ShotAndShoot.global.dto.ResultMessageDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/v1/member")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    /**
     * 모든 회원 단순 조회
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<List<MemberResponseDTO>> getAllMember(){
        List<MemberResponseDTO> members = memberService.getAllMember();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    /**
     * 회원가입, 시큐리티 적용 전 임시 데이터 저장용
     *
     * @param memberRequestDTO
     */
    @PostMapping("/register")
    public ResponseEntity<ResultMessageDTO> register(@RequestBody MemberRequestDTO memberRequestDTO) {
        try {
            String message = memberService.register(memberRequestDTO);
            return new ResponseEntity<>(new ResultMessageDTO(message), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(new ResultMessageDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 회원탈퇴, 수정필요
     * @param memberId
     */
    @DeleteMapping("/unregister/{memberId}")
    public ResponseEntity<ResultMessageDTO> unregister(@PathVariable Long memberId) {
        try {
            String message = memberService.unregister(memberId);
            return new ResponseEntity<>(new ResultMessageDTO(message), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(new ResultMessageDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
