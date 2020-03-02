package com.example.demo.dao;

import com.example.demo.model.Student;
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
public class StudentJdbc {
    private final JdbcTemplate jdbcTemplate;

    public StudentJdbc(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public Student get(int id){
        return jdbcTemplate.queryForObject("SELECT * FROM student WHERE id = ?", this::mapStudent, id);
    }

    public List<Student> getAll(){
        return jdbcTemplate.query("SELECT * FROM student", ROW_MAPPER);
    }

    public List<Student> getByStudyGroupId(int study_group_id){
        return jdbcTemplate.query("SELECT * FROM student WHERE study_group_id = ?", ROW_MAPPER, study_group_id);
    }

    public Student save(Student student) {
        if (student.getId() == 0) {
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("surname", student.getSurname());
            parameters.put("name", student.getName());
            parameters.put("second_name", student.getSecond_name());
            parameters.put("study_group_id", student.getStudy_group_id());
            SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
            jdbcInsert.withTableName("student")
                    .usingColumns("surname", "name", "second_name", "study_group_id")
                    .usingGeneratedKeyColumns("id");
            int id = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters)).intValue();
            student.setId(id);
        } else {
            jdbcTemplate.update("UPDATE student SET surname = ?2, name = ?3, second_name = ?4, study_group_id = ?5 WHERE id = ?1",
                    student.getId(), student.getSurname(), student.getName(), student.getSecond_name(), student.getStudy_group_id());
        }
        return get(student.getId());
    }


    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM student WHERE id = ?", id);
    }

    private Student mapStudent(ResultSet rs, int i) throws SQLException
    {
        return new Student(
                rs.getInt("id"),
                rs.getString("surname"),
                rs.getString("name"),
                rs.getString("second_name"),
                rs.getInt("study_group_id")
        );
    }
    RowMapper<Student> ROW_MAPPER = (ResultSet rs, int rowNum) -> {
        return new Student(
                rs.getInt("id"),
                rs.getString("surname"),
                rs.getString("name"),
                rs.getString("second_name"),
                rs.getInt("study_group_id")
        );
    };
}
