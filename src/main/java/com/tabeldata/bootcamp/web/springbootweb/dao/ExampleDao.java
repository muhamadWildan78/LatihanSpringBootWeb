package com.tabeldata.bootcamp.web.springbootweb.dao;
import com.tabeldata.bootcamp.web.springbootweb.model.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class ExampleDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<Example> list() {
        String sql = "select * from example";
        return this.jdbcTemplate.query(sql, new RowMapperInner());
    }

    public Example findById(String id) {
        String sql = "select * from example where id = :kode";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("kode", id);
        return this.jdbcTemplate.queryForObject(sql, map, new RowMapperInner());
    }

    public void insert(Example data) {
        String sql = "insert into example(id, nama, last_update, last_date, salary, description, is_active) values (:kode, :nama, :last_update, :last_date, :salary, :descrition, :active)";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("kode", data.getId());
        map.addValue("nama", data.getOther());
        map.addValue("last_update", data.getTransactionDatetime());
        map.addValue("last_date", data.getTransactionDate());
        map.addValue("salary", data.getSalary());
        map.addValue("descrition", data.getMessage());
        map.addValue("active", data.getIsActive());
        this.jdbcTemplate.update(sql, map);
    }

    public void update(Example data) {
        String sql = "update example set nama = :nama, last_update = :last_update where id = :kode";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("kode", data.getId());
        map.addValue("nama", data.getOther());
        map.addValue("last_update", data.getTransactionDatetime());
        this.jdbcTemplate.update(sql, map);
    }

    public void delete(String id) {
        String sql = "delete from example where id = :kode";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("kode", id);
        this.jdbcTemplate.update(sql, map);
    }

    public class RowMapperInner implements RowMapper<Example> {

        @Override
        public Example mapRow(ResultSet rs, int rowNum) throws SQLException {
            Example data = new Example();
            data.setId(rs.getString("id"));
            data.setMessage(rs.getString("description"));
            data.setOther(rs.getString("nama"));
            Date lastDate = rs.getDate("last_date");
            if (lastDate != null)
                data.setTransactionDate(lastDate.toLocalDate());
            Timestamp lastUpdate = rs.getTimestamp("last_update");
            if (lastUpdate != null)
                data.setTransactionDatetime(lastUpdate.toLocalDateTime());
            data.setIsActive(rs.getBoolean("is_active"));
            return data;
        }
    }
}
