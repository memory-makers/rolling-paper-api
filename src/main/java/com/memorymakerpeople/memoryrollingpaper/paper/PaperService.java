package com.memorymakerpeople.memoryrollingpaper.paper;

import com.memorymakerpeople.memoryrollingpaper.card.CardRepository;
import com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus;
import com.memorymakerpeople.memoryrollingpaper.paper.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaperService {

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private PaperDao paperDao;



    public PostPaperRes createPaper(PostPaperReq postPaperReq, String id) {
        System.out.println("postPaperReq = " + postPaperReq);
        System.out.println("id = " + id);
        postPaperReq.getPaper().setUserId(id);
        postPaperReq.getPaper().setPaperUrl(UUID.randomUUID().toString());
        return new PostPaperRes(paperRepository.save(postPaperReq.getPaper()), BaseResponseStatus.SUCCESS);
    }

    public List<Paper> selectPaper(String id){
        return paperRepository.findByUserId(id);
    }

   public GetPaperRes selectOnePaper(BigInteger paperId){
       System.out.println("paperId = " + paperId);
       Optional<Paper> paper = paperRepository.findByPaperId(paperId);
       if (paper.isPresent()) {
           return new GetPaperRes(paper.get(), BaseResponseStatus.SUCCESS);
       }

       return new GetPaperRes(null, BaseResponseStatus.EMPTY_PAPER_ID);
    }

    public PutPaperRes updatePaper(PutPaperReq putPaperReq, String email) {
        //생성한 사람이 아닌 사람이 수정을 시도하는 경우
        if(paperDao.checkEmailAndPaperId(email, putPaperReq.getPaper().getPaperId())) {
            return new PutPaperRes(paperRepository.save(putPaperReq.getPaper()), BaseResponseStatus.SUCCESS);
        }

        return new PutPaperRes(null, BaseResponseStatus.FAILED_TO_PAPER_UPDATE);
    }

    public GetpaperIdRes selectPaperId(String paperUrl) {
        PaperIdMapping byPaperUrl = paperRepository.findByPaperUrl(paperUrl);
        return new GetpaperIdRes(byPaperUrl.getPaperId(),BaseResponseStatus.SUCCESS);
    }

    /*public PaperResponseDto deletePaper(Paper paper) {
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
    }*/
}
