package com.memorymakerpeople.memoryrollingpaper.utils;

import com.memorymakerpeople.memoryrollingpaper.card.model.PostCardResponse;
import com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus;
import com.memorymakerpeople.memoryrollingpaper.paper.PaperRepository;
import com.memorymakerpeople.memoryrollingpaper.paper.model.Paper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Optional;

public class ValidUtil {

    /**
     * paper 오픈 예정일이 지났는지 validation
     * @param Paper
     * @return
     */
    public static BaseResponseStatus validCardDueDate(Optional<Paper> Paper) {
        //카드 오픈 예정일이 오늘이 지났으면 저장하지 않음
        if (Paper.isPresent()) {
            if(Paper.get().getDueDate().after(new Timestamp(System.currentTimeMillis()))) {
                return BaseResponseStatus.INVALID_CARD_DUE_DATE;
            }
        }
        return null;
    }
}
