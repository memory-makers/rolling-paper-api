package com.memorymakerpeople.memoryrollingpaper.repository;

import com.memorymakerpeople.memoryrollingpaper.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
    boolean existsByEmail(String email);
    List<Member> findAll();
}
