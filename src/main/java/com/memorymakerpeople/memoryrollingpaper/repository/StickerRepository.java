package com.memorymakerpeople.memoryrollingpaper.repository;

import com.memorymakerpeople.memoryrollingpaper.domain.Card;
import com.memorymakerpeople.memoryrollingpaper.domain.Sticker;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface StickerRepository extends JpaRepository<Sticker, Integer> {
    Optional<Sticker> findByStickerId(int stickerId); //아아디로 된 페이퍼 모두 찾기
    List<Sticker> findByPaperId(int paperId); //아아디로 된 페이퍼 모두 찾기
}
