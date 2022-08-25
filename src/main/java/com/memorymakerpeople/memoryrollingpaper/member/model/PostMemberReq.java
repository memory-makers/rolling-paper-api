package com.memorymakerpeople.memoryrollingpaper.member.model;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostMemberReq {
    private String username;
    private String email;
    private String password;

    public PostMemberReq(String id, String email) {
        this.username = id;
        this.email = email;
    }
}