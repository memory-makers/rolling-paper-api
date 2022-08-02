package com.memorymakerpeople.memoryrollingpaper.dto;

import lombok.Data;

@Data
public class MemberRequestDto{
    public Integer id;
    public String username;//아이디
    private String nickname;
}
