package com.memorymakerpeople.memoryrollingpaper.authLogin;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class UserLoginRes extends User {
    BigInteger id;
    String email;
    String nickname;

    public UserLoginRes(BigInteger id, String username, String password, String nickname, Collection<?
            extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
        this.email = username;
        this.nickname = nickname;
    }
}
