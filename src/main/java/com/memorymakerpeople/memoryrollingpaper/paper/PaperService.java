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

    public PostPaperRes createPaper(PostPaperReq postPaperReq, String userId) {
        postPaperReq.changeIdAndUrl(userId, UUID.randomUUID().toString());
        Paper paper = paperRepository.save(postPaperReq.toEntity());
        return new PostPaperRes(paper.getPaperId(), BaseResponseStatus.SUCCESS);
    }

    public List<Paper> selectPaper(String id){
        return paperRepository.findByUserIdAndDeleteYn(id, "N");
    }

   public GetPaperRes selectOnePaper(Long paperId){
       Optional<Paper> paper = paperRepository.findByPaperId(paperId);
       if (paper.isPresent()) {
           GetPaperRes paperRes = paper.get().toGetPaperRes();
           paperRes.updateStatus(BaseResponseStatus.SUCCESS);
           return paperRes;
       }
       return null; //에러처리 필요
    }

    //리펙토링 필요
    public PutPaperRes updatePaper(PutPaperReq putPaperReq, String email) {
        //생성한 사람이 아닌 사람이 수정을 시도하는 경우
        if(paperDao.checkEmailAndPaperId(email, putPaperReq.getPaperId())) {
            Optional<Paper> paper = paperRepository.findByPaperId(putPaperReq.getPaperId());

            if(paper.isPresent()) {
                Paper savedPaper = paperRepository.save(putPaperReq.toEntity());

                return new PutPaperRes(savedPaper.getPaperId(), BaseResponseStatus.SUCCESS);
            }
        }

        return new PutPaperRes(null, BaseResponseStatus.FAILED_TO_PAPER_UPDATE);
    }

    //리펙토링 필요
    public GetpaperIdRes selectPaperId(String paperUrl) {
        PaperIdMapping byPaperUrl = paperRepository.findByPaperUrl(paperUrl);
        return new GetpaperIdRes(byPaperUrl.getPaperId(),BaseResponseStatus.SUCCESS);
    }

    //리펙토링 필요
    public DeletePaperRes deletePaper(Long paperId, String email) {

        if(paperDao.checkEmailAndPaperId(email, paperId)) {
            Optional<Paper> paperOptional = paperRepository.findByPaperId(paperId);

            if (paperOptional.isPresent()) {
                paperOptional.get().softRemovePaper();
                Paper savedPaper = paperRepository.save(paperOptional.get());

                return new DeletePaperRes(savedPaper.getPaperId(), BaseResponseStatus.SUCCESS);
            }
        }

        return new DeletePaperRes(null, BaseResponseStatus.FAILED_TO_PAPER_UPDATE);
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
