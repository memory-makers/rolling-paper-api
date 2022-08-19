package com.memorymakerpeople.memoryrollingpaper.member;

import com.memorymakerpeople.memoryrollingpaper.member.model.Member;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class MemberSessionDto implements Serializable {
    private String username;
    private String nickname;

    /* Entity -> Dto */
    public MemberSessionDto(Member member) {
        this.username = member.getUsername();
        this.nickname = member.getNickname();
    }
}