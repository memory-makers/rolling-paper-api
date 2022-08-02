package com.memorymakerpeople.memoryrollingpaper.domain;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Entity
@DynamicUpdate // 변경한 필드만 대응
@Table(name = "tbl_user")
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String username;//아이디
    private String nickname;

}