package com.memorymakerpeople.memoryrollingpaper.util;

import com.memorymakerpeople.memoryrollingpaper.domain.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {

    public static String GetLoginId(HttpServletRequest request){
        HttpSession session = request.getSession();
        Member sessionAttribute = (Member) session.getAttribute(SessionConstants.LOGIN_MEMBER);
        String loginId = sessionAttribute.getUsername();
        return loginId;
    }

    public static Member GetSessionMember(HttpServletRequest request){
        HttpSession session = request.getSession();
        return (Member) session.getAttribute(SessionConstants.LOGIN_MEMBER);
    }
}
