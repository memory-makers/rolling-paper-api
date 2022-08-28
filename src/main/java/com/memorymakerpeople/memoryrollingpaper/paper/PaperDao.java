package com.memorymakerpeople.memoryrollingpaper.paper;

import com.memorymakerpeople.memoryrollingpaper.authLogin.UserLoginRes;
import com.memorymakerpeople.memoryrollingpaper.member.model.Authority;
import com.memorymakerpeople.memoryrollingpaper.member.model.PostMemberReq;
import com.memorymakerpeople.memoryrollingpaper.member.model.PostMemberRes;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class PaperDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Boolean checkEmailAndPaperId(String email, int paperId) {
        String checkEmailQuery = "select exists" +
                                    "(select p.paper_id " +
                                    "from tbl_paper p join tbl_user u" +
                                    "on p.user_id = u.email " +
                                    "where u.email = ? " +
                                    "AND p.paper_id = ?)";
        Integer findResult = this.jdbcTemplate.queryForObject(checkEmailQuery,
                int.class,
                email,
                paperId);
        if (findResult > 0) {
            return true;
        }

        return false;
    }

}
