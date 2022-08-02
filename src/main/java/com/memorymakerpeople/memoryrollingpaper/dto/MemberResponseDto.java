package com.memorymakerpeople.memoryrollingpaper.dto;

import com.memorymakerpeople.memoryrollingpaper.domain.Member;
import lombok.Data;

@Data
public class MemberResponseDto extends DefaultResponseDto {
    Member member;
}
