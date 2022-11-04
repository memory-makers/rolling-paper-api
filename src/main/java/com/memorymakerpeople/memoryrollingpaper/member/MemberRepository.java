package com.memorymakerpeople.memoryrollingpaper.member;

import com.memorymakerpeople.memoryrollingpaper.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByEmail(String email);
    List<Member> findAll();
    Optional<Member> findByEmail(String email);

    Optional<Member> findById(BigInteger id);
}
