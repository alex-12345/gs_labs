package com.example.demo.dao;

import com.example.demo.model.StudyGroup;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Repository
public class StudyGroupJdbc {
    private final JdbcTemplate jdbcTemplate;

    public StudyGroupJdbc(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public StudyGroup get(int id){
        return jdbcTemplate.queryForObject("SELECT * FROM study_group WHERE id = ?", this::mapStudyGroup, id);
    }

    public List<StudyGroup> getAll(){
        return jdbcTemplate.query("SELECT * FROM study_group", ROW_MAPPER);
    }

    public StudyGroup save(StudyGroup studyGroup) {
        if (studyGroup.getId() == 0) {
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("name", studyGroup.getName());
            SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
            jdbcInsert.withTableName("study_group").usingColumns("name").usingGeneratedKeyColumns("id");
            int id = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters)).intValue();
            studyGroup.setId(id);
        } else {
           jdbcTemplate.update("UPDATE study_group SET name = ?2 WHERE id = ?1", studyGroup.getId(), studyGroup.getName());
        }

        return get(studyGroup.getId());
    }
    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM study_group WHERE id = ?", id);
    }

    private StudyGroup mapStudyGroup(ResultSet rs, int i) throws SQLException
    {
        return new StudyGroup(
                rs.getInt("id"),
                rs.getString("name")
        );
    }
    RowMapper<StudyGroup> ROW_MAPPER = (ResultSet rs, int rowNum) -> {
        return new StudyGroup(rs.getInt("id"), rs.getString("name"));
    };
}
