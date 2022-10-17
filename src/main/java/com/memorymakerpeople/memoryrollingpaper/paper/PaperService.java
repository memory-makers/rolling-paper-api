package com.memorymakerpeople.memoryrollingpaper.paper;

import com.memorymakerpeople.memoryrollingpaper.config.BaseResponseStatus;
import com.memorymakerpeople.memoryrollingpaper.member.MemberRepository;
import com.memorymakerpeople.memoryrollingpaper.member.model.Member;
import com.memorymakerpeople.memoryrollingpaper.paper.model.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaperService {

    private final PaperRepository paperRepository;
    private final PaperDao paperDao;
    private final MemberRepository memberRepository;



    //리펙토링 필요
    public PostPaperRes createPaper(PostPaperReq postPaperReq, String id) {
        postPaperReq.getPaper().setUserId(id);
        postPaperReq.getPaper().setPaperUrl(UUID.randomUUID().toString());
        return new PostPaperRes(paperRepository.save(postPaperReq.getPaper()), BaseResponseStatus.SUCCESS);
    }

    public List<Paper> selectPaper(String id){
        return paperRepository.findByUserIdAndDeleteYn(id, "N");
    }

    //리펙토링 필요
   public GetPaperRes selectOnePaper(Long paperId){
       Optional<Paper> paper = paperRepository.findByPaperId(paperId);
       if (paper.isPresent()) {
           return new GetPaperRes(paper.get(), BaseResponseStatus.SUCCESS);
       }

       return new GetPaperRes(null, BaseResponseStatus.EMPTY_PAPER_ID);
    }

    //리펙토링 필요
    public PutPaperRes updatePaper(PutPaperReq putPaperReq, String email) {
        //생성한 사람이 아닌 사람이 수정을 시도하는 경우
        if(paperDao.checkEmailAndPaperId(email, putPaperReq.getPaper().getPaperId())) {
            Optional<Paper> paperOptional = paperRepository.findByPaperId(putPaperReq.getPaper().getPaperId());

            if(paperOptional.isPresent()) {
                Paper paper = paperOptional.get();
                putPaperReq.getPaper().setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                putPaperReq.getPaper().setDeleteYn(paper.getDeleteYn());
                putPaperReq.getPaper().setUserId(paper.getUserId());
            }
            return new PutPaperRes(paperRepository.save(putPaperReq.getPaper()), BaseResponseStatus.SUCCESS);
        }
        return new PutPaperRes(null, BaseResponseStatus.FAILED_TO_PAPER_UPDATE);
    }

    //리펙토링 필요
    public GetpaperIdRes selectPaperId(String paperUrl) {
        PaperIdMapping byPaperUrl = paperRepository.findByPaperUrl(paperUrl);
        return new GetpaperIdRes(byPaperUrl.getPaperId(),BaseResponseStatus.SUCCESS);
    }

    //리펙토링 필요
    public DeletePaperRes deletePaper(Long paperId, String userId) {
        //인증 해줘야함
        Optional<Paper> paperOptional = paperRepository.findByPaperId(paperId);

        if(paperOptional.isPresent()) {
            paperOptional.get().setDeleteYn("Y");
        }

        return new DeletePaperRes(paperRepository.save(paperOptional.get()), BaseResponseStatus.SUCCESS);
    }

    //리펙토링 필요
    public GetPaperNicknameRes selectNickname(String paperId) {
        Optional<Paper> paper = paperRepository.findByPaperId(Long.parseLong(paperId));
        if (paper.isPresent()) {
            String userId = paper.get().getUserId();
            Member member = memberRepository.findByEmail(userId);
            String nickname = member.getNickname();
            return new GetPaperNicknameRes(nickname,BaseResponseStatus.SUCCESS);
        }
        return new GetPaperNicknameRes(null,BaseResponseStatus.EMPTY_PAPER_ID);
    }
}
