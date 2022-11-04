package com.memorymakerpeople.memoryrollingpaper.paper;

import com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus;
import com.memorymakerpeople.memoryrollingpaper.exception.CustomException;
import com.memorymakerpeople.memoryrollingpaper.member.MemberRepository;
import com.memorymakerpeople.memoryrollingpaper.member.model.Member;
import com.memorymakerpeople.memoryrollingpaper.paper.model.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class PaperService {

    private final PaperRepository paperRepository;
    private final PaperDao paperDao;
    private final MemberRepository memberRepository;

    public PostPaperRes createPaper(PostPaperReq postPaperReq, String userId) {
        postPaperReq.changeIdAndUrl(userId, UUID.randomUUID().toString());
        Paper paper = paperRepository.save(postPaperReq.toEntity());

        return new PostPaperRes(paper.getPaperId(), paper.getPaperUrl());
    }

    public List<GetPaperRes> selectPaper(String id){
        List<Paper> papers = paperRepository.findByUserIdAndDeleteYn(id, "N");

        if(papers.isEmpty()) {
            return null;
        }
        List<GetPaperRes> getPaperRes = convertPaperToGetPaperRes(papers);

        return getPaperRes;
    }

    public GetPaperRes selectOnePaper(Long paperId){
       Optional<Paper> paper = paperRepository.findByPaperId(paperId);

       if (paper.isEmpty()) {
           return null;
       }
        GetPaperRes paperRes = paper.get().toGetPaperRes();

        return paperRes;
    }

    public PutPaperRes updatePaper(PutPaperReq putPaperReq, String email) {
        //생성한 사람이 아닌 사람이 수정을 시도하는 경우
        if(!paperDao.checkEmailAndPaperId(email, putPaperReq.getPaperId())) {
            throw new CustomException(FAILED_TO_PAPER_UPDATE);
        }

        Optional<Paper> paper = paperRepository.findByPaperId(putPaperReq.getPaperId());

        if(paper.isEmpty()) {
            return null;
        }
        Paper savedPaper = paperRepository.save(putPaperReq.toEntity(email));

        return new PutPaperRes(savedPaper.getPaperId());
    }

    public GetpaperIdRes selectPaperId(String paperUrl) {
        PaperIdMapping paper = paperRepository.findByPaperUrl(paperUrl);
        Long paperId = paper.getPaperId();

        if (paperId == null) {
            throw new CustomException(FOUND_URL_INFO_NULL);
        }

        return new GetpaperIdRes(paperId);
    }

    public DeletePaperRes deletePaper(Long paperId, String email) {

        if(!paperDao.checkEmailAndPaperId(email, paperId)) {
            throw new CustomException(FAILED_TO_PAPER_UPDATE);
        }

        Optional<Paper> paperOptional = paperRepository.findByPaperId(paperId);
        if (paperOptional.isEmpty()) {
            return null;
        }

        Paper paper = paperOptional.get();
        paper.softRemovePaper();
        Paper savedPaper = paperRepository.save(paper);

        return new DeletePaperRes(savedPaper.getPaperId());
    }

    //리펙토링 필요
    public GetPaperNicknameRes selectNickname(Long paperId) {
        Optional<Paper> optionalPaper = paperRepository.findByPaperId(paperId);

        if (optionalPaper.isEmpty()) {
            throw new CustomException(FOUND_PAPER_INFO_NULL);
        }
        String userId = optionalPaper.get().getUserId();
        Optional<Member> optionalMember = memberRepository.findByEmail(userId);

        if (optionalMember.isEmpty()) {
            throw new CustomException(FOUND_NICKNAME_INFO_NULL);
        }
        String nickname = optionalMember.get().getNickname();
        return new GetPaperNicknameRes(nickname);
    }



    private List<GetPaperRes> convertPaperToGetPaperRes(List<Paper> papers) {
        List<GetPaperRes> paperResList = new ArrayList<>();

        for (Paper paper : papers) {
            paperResList.add(paper.toGetPaperRes());
        }
        return paperResList;
    }
}
