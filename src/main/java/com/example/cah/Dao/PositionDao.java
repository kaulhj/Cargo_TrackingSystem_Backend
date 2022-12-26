package com.example.cah.Dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class PositionDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired //readme 참고
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int getLastEndTag(){
        String Query = "select endTag from\n" +
                "Position\n" +
                "order by positionIdx desc limit 1";

        return this.jdbcTemplate.queryForObject(Query,int.class);
    }

    public int getLastMNum(){
        String q = "select measureId from\n" +
                "Position\n " +
                "order by positionIdx desc limit 1";

        return this.jdbcTemplate.queryForObject(q,int.class);
    }


}
