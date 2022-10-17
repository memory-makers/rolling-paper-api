package com.memorymakerpeople.memoryrollingpaper.paper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
@Slf4j
public class PaperDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Boolean checkEmailAndPaperId(String email, Long paperId) {
        String checkEmailQuery = "select exists" +
                                    "(select p.paper_id " +
                                    "from tbl_paper p join tbl_user u " +
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
