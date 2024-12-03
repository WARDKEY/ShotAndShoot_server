package com.example.ShotAndShoot.domain.member.repsitory;

import com.example.ShotAndShoot.global.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByName(String name);
}
