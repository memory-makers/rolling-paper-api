package com.memorymakerpeople.memoryrollingpaper.authLogin;

import com.memorymakerpeople.memoryrollingpaper.member.MemberDao;
import com.memorymakerpeople.memoryrollingpaper.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    MemberDao memberDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserLoginRes userLoginRes = memberDao.findByEmail(username);
        log.info("userLoginRes = {}", userLoginRes);

        if (userLoginRes != null) {
            log.info("유저 정보 반환");
            return new UserLoginRes(userLoginRes.getId(), userLoginRes.getEmail(), userLoginRes.getPassword(), userLoginRes.getNickname(),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
