package com.memorymakerpeople.memoryrollingpaper.paper;

import com.memorymakerpeople.memoryrollingpaper.card.CardRepository;
import com.memorymakerpeople.memoryrollingpaper.paper.model.Paper;
import com.memorymakerpeople.memoryrollingpaper.paper.model.postPaperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class PaperService {

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private CardRepository cardRepository;

    public postPaperResponse createPaper(Paper paper, BigInteger id) {
        paper.setUserId(id);
        return new postPaperResponse(paperRepository.save(paper),1);
    }

    public List<Paper> selectPaper(BigInteger id){
        return paperRepository.findByUserId(id);
    }

    public PaperResponseDto selectOnePaper(PaperRequestDto paper){
        PaperResponseDto paperResponseDto = new PaperResponseDto();
        String paperId = String.valueOf(paper.getPaperId());
        paperResponseDto.setCardList(cardRepository.findByPaperId(paperId));
        *//*paperResponseDto.setUserId(paper.getUserId());
        paperResponseDto.setPaperUrl(paper.getPaperUrl());
        paperResponseDto.setPaperTitle(paper.getPaperTitle());
        paperResponseDto.setPaperId(paper.getPaperId());
        paperResponseDto.setDueDt(paper.getDueDt());
        paperResponseDto.setTheme(paper.getTheme());
        paperResponseDto.setOpenStatus(paper.getOpenStatus());*//*
        return paperResponseDto;
    }

    public PaperResponseDto updatePaper(Paper paper) {
        PaperResponseDto result = new PaperResponseDto();
        Paper save = paperRepository.save(paper);
        result.message = "Update Paper";
        if (save == null){
            result.statusCode = "fail";
        }else{
            result.statusCode = "complete";
        }

        return result;
    }

    public PaperResponseDto deletePaper(Paper paper) {
        PaperResponseDto result = new PaperResponseDto();
        paper.setDeleteYn("Y");
        Paper save = paperRepository.save(paper);
        result.message = "Delete Paper";
        if (save == null){
            result.statusCode = "fail";
        }else{
            result.statusCode = "complete";
        }

        return result;
    }
}
