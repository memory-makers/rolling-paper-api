package com.memorymakerpeople.memoryrollingpaper.sticker;

import com.memorymakerpeople.memoryrollingpaper.sticker.model.Sticker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StickerRepository extends JpaRepository<Sticker, Integer> {
    Optional<Sticker> findByStickerId(int stickerId);
    List<Sticker> findByPaperId(int paperId); //아아디로 된 페이퍼 모두 찾기
}
