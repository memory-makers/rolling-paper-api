package com.memorymakerpeople.memoryrollingpaper.member.model;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Entity
@DynamicInsert // null로 들어가야 하는 경우 대응(기본값)
@DynamicUpdate // 변경한 필드만 대응
@Table(name = "tbl_user")
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;
    @NotNull
    private String email;
    private String nickname;
    private String password;
    private String kakaoAccessToken;
    private String kakaoId;

    @Builder
    public Member(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }
}