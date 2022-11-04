package com.memorymakerpeople.memoryrollingpaper.config;

import com.memorymakerpeople.memoryrollingpaper.authLogin.UserLoginRes;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class JwtTokenUtil implements Serializable {
    private static final long serialVersionUID = -2550185165626007488L;
    public static final long JWT_TOKEN_VALIDITY = 1 * 60 * 60;
    @Value("${jwt.secret}")
    private String secret;

    //jwt 토큰에서 사용자 이름 검색.
    public String getUsernameFromToken(String token) {
        log.debug("token = {}", token);
        return getClaimFromToken(token, Claims::getSubject);
    }

    //jwt 토큰에서 만료 날짜 검색.
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        log.debug("claims = {}", claims);
        return claimsResolver.apply(claims);
    }

    // JWT에서 회원 정보 추출.
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //Jwt 생성.
    public String generateToken(UserDetails userDetails) {
        UserLoginRes userLoginRes = (UserLoginRes) userDetails;
        Map<String, Object> claims = new HashMap<>();
        claims.put("social", "email");
        claims.put("email", userLoginRes.getEmail());
        claims.put("id", userLoginRes.getId());
        claims.put("username", userLoginRes.getUsername());
        claims.put("nickname", userLoginRes.getNickname());
        return doGenerateToken(claims, userLoginRes.getUsername());
    }

    //Jwt 발급.
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis() * 1000))
                .setExpiration(new Date((System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000) * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    // 토큰유효성 + 만료일자 확인.
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}











