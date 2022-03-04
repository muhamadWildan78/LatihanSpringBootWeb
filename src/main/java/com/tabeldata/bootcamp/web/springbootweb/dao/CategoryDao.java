package com.tabeldata.bootcamp.web.springbootweb.dao;
import com.tabeldata.bootcamp.web.springbootweb.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CategoryDao {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<Category> list() {
        String sql = "select * from category";
        return this.jdbcTemplate.query(sql, new CategoryDao.RowMapperInner());
    }

    public Category findById(String id) {
        String sql = "select * from category where category_id = :kode";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("kode", id);
        return this.jdbcTemplate.queryForObject(sql, map, new CategoryDao.RowMapperInner());
    }

    public void insertCategory(Category data) {
        String sql = "insert into category(category_id, department_id, name, description) values (:kodeid, :department, :name, :description)";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("kodeid", data.getCategory_id());
        map.addValue("department", data.getDepartment_id());
        map.addValue("name", data.getName());
        map.addValue("description", data.getDescription());
        this.jdbcTemplate.update(sql, map);
    }

    public void updateCategory(Category data) {
        String sql = "update category set name = :name, description = :description where category_id = :kodeid";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("kodeid", data.getCategory_id());
        map.addValue("department", data.getDepartment_id());
        map.addValue("name", data.getName());
        map.addValue("description", data.getDescription());
        this.jdbcTemplate.update(sql, map);
    }
    public void deleteCategory(String categoryId) {
        String sql = "delete from category where category_id = :kodeid";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("kodeid", categoryId);
        this.jdbcTemplate.update(sql, map);
    }

    public class RowMapperInner implements RowMapper<Category> {

        @Override
        public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
            Category data = new Category();
            data.setCategory_id(rs.getInt("category_id"));
            data.setDepartment_id(rs.getInt("department_id"));
            data.setName(rs.getString("name"));
            data.setDescription(rs.getString("description"));
            return data;
        }
    }
}
