package com.memorymakerpeople.memoryrollingpaper.member.model;

import lombok.Data;

@Data
public class MemberResponseDto {
    public String statusCode;
    Member member;
    public String message;
}
