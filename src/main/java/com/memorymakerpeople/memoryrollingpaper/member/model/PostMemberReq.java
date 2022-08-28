package com.memorymakerpeople.memoryrollingpaper.member.model;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostMemberReq {
    private String email;
    private String password;

    public PostMemberReq(String email) {
        this.email = email;
    }
}