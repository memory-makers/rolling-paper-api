package com.memorymakerpeople.memoryrollingpaper.utils;

import com.memorymakerpeople.memoryrollingpaper.card.model.PostCardResponse;
import com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus;
import com.memorymakerpeople.memoryrollingpaper.paper.PaperRepository;
import com.memorymakerpeople.memoryrollingpaper.paper.model.Paper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Optional;

@Slf4j
public class ValidUtil {


    public static boolean validCardDueDate(Paper Paper) {
        Timestamp dueDate = Paper.getDueDate();

        if(dueDate.before(new Timestamp(System.currentTimeMillis()))) {
            return true;
        }
        return true;
    }
}
