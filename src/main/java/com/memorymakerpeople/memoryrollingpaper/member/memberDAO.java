package com.memorymakerpeople.memoryrollingpaper.member;

import com.memorymakerpeople.memoryrollingpaper.authLogin.UserLoginRes;
import com.memorymakerpeople.memoryrollingpaper.member.model.Authority;
import com.memorymakerpeople.memoryrollingpaper.member.model.PostMemberReq;
import com.memorymakerpeople.memoryrollingpaper.member.model.PostMemberRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

@Repository
public class MemberDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public PostMemberRes createMember(PostMemberReq postMemberReq) {

        String createMemberQuery = "insert into tbl_user (email, password, username ) VALUES (?, ?, ?)";
        Object[] createMemberParams = new Object[]{postMemberReq.getEmail(), "kakao", postMemberReq.getUsername()
        };
        this.jdbcTemplate.update(createMemberQuery, createMemberParams);

        String getLastInsertIdxQuery = "select last_insert_id()";
        int lastInsertIdx = this.jdbcTemplate.queryForObject(getLastInsertIdxQuery, int.class);

        String createAuthorityQuery = "insert into authority values(?, ?)";
        Object[] createAuthorityParams = new Object[]{lastInsertIdx, 0};
        this.jdbcTemplate.update(createAuthorityQuery, createAuthorityParams);
        return new PostMemberRes(lastInsertIdx, 1);
    }

    public UserLoginRes findByEmail(String email) {
        String getEmailQuery = "SELECT * FROM tbl_user LEFT OUTER JOIN authority on tbl_user.id=authority.member_idx WHERE email=?";

        return this.jdbcTemplate.queryForObject(getEmailQuery
                , (rs, rowNum) -> new UserLoginRes(
                        rs.getObject("id", BigInteger.class),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("nickname"),
                        rs.getString("password"),
                        Arrays.asList(new SimpleGrantedAuthority(Authority.values()[rs.getObject("role", int.class)].toString()))
                ), email);
    }

    public Boolean getUserEmail(String email) {
        String findEmailQuery = "SELECT * FROM tbl_user WHERE email=?";
        try {
            UserLoginRes userLoginRes = this.jdbcTemplate.queryForObject(findEmailQuery
                    , (rs, rowNum) -> new UserLoginRes(
                            rs.getObject("id", BigInteger.class),
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("nickname"),
                            rs.getString("password"),
                            new ArrayList<>()
                    ), email);
            if (userLoginRes.getEmail() != null) {
                return true;
            } else {
                return false;
            }
        } catch (EmptyResultDataAccessException e) {
            return false;

        }
    }
}
