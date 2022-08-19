package com.memorymakerpeople.memoryrollingpaper.member.model;

import com.memorymakerpeople.memoryrollingpaper.authLogin.UserLoginRes;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.math.BigInteger;
import java.util.Collection;

@Getter
@Setter

public class UserLoginResWithStatus {
    UserLoginRes userLoginRes;
    Integer status;

    public UserLoginResWithStatus(BigInteger id, String username, String password, String email, String nickname, Collection<?
            extends GrantedAuthority> authorities, Integer status) {
        this.userLoginRes = new UserLoginRes(id, username, password, email, nickname, authorities);
        this.status = status;
    }
}
