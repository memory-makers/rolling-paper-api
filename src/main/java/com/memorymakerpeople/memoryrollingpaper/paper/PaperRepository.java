package com.memorymakerpeople.memoryrollingpaper.paper;

import com.memorymakerpeople.memoryrollingpaper.paper.model.Paper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface PaperRepository extends JpaRepository<Paper, Integer> {
    Optional<Paper> findByPaperId(int paperId); //하나만 찾기
    List<Paper> findByUserId(BigInteger userId); //아아디로 된 페이퍼 모두 찾기
}
