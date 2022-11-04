package com.memorymakerpeople.memoryrollingpaper.member;

import com.memorymakerpeople.memoryrollingpaper.authLogin.UserLoginRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberProvider {
    private final MemberDao memberDao;

    @Autowired
    public MemberProvider(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public UserLoginRes findByEmail(String email){
        try{
            return memberDao.findByEmail(email);
        } catch (Exception exception){
            throw exception;
        }
    }


}
