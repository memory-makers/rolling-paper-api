package com.memorymakerpeople.memoryrollingpaper.paper;

import com.memorymakerpeople.memoryrollingpaper.paper.model.Paper;
import com.memorymakerpeople.memoryrollingpaper.paper.model.PaperIdMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaperRepository extends JpaRepository<Paper, Long> {
    Optional<Paper> findByPaperId(Long paperId); //하나만 찾기
    List<Paper> findByUserIdAndDeleteYn(String userId, String deleteYn); //아아디로 된 페이퍼 모두 찾기
    PaperIdMapping findByPaperUrl(String url);
    boolean existsByPaperId(Long paperId);
}
