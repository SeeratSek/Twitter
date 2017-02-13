package com.twitter.repositories;

import com.twitter.models.Pie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

// The PieRepository is an interface that extends CrudRepository which provides you with functionality such as findAll() or findOne().
/**
 * Created by seeratsekhon on 2017-02-12.
 */
@Repository
public abstract class PieRepository implements CrudRepository<Pie, Long> {
    //List<Pie> findByName(String name);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly=true)
    public List<Pie> findAll() {
        return jdbcTemplate.query("select * from pies",
                new PieRowMapper());
    }
}

class PieRowMapper implements RowMapper<Pie>
{
    @Override
    public Pie mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pie pie = new Pie(rs.getString("name"), rs.getString("description"));

        return pie;
    }
}