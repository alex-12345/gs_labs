package com.example.demo.dao;

import com.example.demo.model.Journal;
import com.example.demo.model.JournalFull;
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
public class JournalJdbc {
    private final JdbcTemplate jdbcTemplate;

    public JournalJdbc(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public Journal get(int id){
        return jdbcTemplate.queryForObject("SELECT * FROM journal WHERE id = ?", this::mapJournal, id);
    }
    public List<Journal> getAllByStudent(int student_id) {
        return jdbcTemplate.query("SELECT * FROM journal WHERE student_id = ?", ROW_MAPPER, student_id);
    }

    public List<JournalFull> getAllByStudyGroup(int study_group_id) {
        return jdbcTemplate.query("SELECT journal.id, journal.student_id, journal.study_plan_id," +
                "       journal.in_time, journal.count, journal.mark_id, mark.name, mark.value, study_plan.exam_type_id, study_plan.subject_id, exam_type.type, subject.name full_name, subject.short_name " +
                " FROM ((((journal INNER JOIN mark ON journal.mark_id = mark.id) INNER JOIN study_plan ON journal.study_plan_id = study_plan.id) " +
                "INNER JOIN exam_type ON study_plan.exam_type_id = exam_type.id) INNER JOIN subject ON study_plan.subject_id = subject.id) " +
                " INNER JOIN student ON journal.student_id = student.id " +
                " WHERE study_group_id = ?", ROW_MAPPER_BY_STUDY_GROUP, study_group_id);
    }

    public Journal save(Journal journal) {
        if (journal.getId() == 0) {
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("student_id", journal.getStudent_id());
            parameters.put("study_plan_id", journal.getStudy_plan_id());
            parameters.put("in_time", journal.isIn_time());
            parameters.put("count", journal.getCount());
            parameters.put("mark_id", journal.getMark_id());
            SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
            jdbcInsert.withTableName("journal")
                    .usingColumns("student_id", "study_plan_id", "in_time", "count", "mark_id")
                    .usingGeneratedKeyColumns("id");
            int id = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters)).intValue();
            journal.setId(id);
        } else {
            jdbcTemplate.update("UPDATE journal SET student_id = ?2, study_plan_id = ?3, in_time = ?4, count = ?5, mark_id =?6 WHERE id = ?1",
                    journal.getId(), journal.getStudent_id(), journal.getStudy_plan_id(), journal.isIn_time(), journal.getCount(), journal.getMark_id());
        }
        return get(journal.getId());
    }


    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM journal WHERE id = ?", id);
    }

    private Journal mapJournal(ResultSet rs, int i) throws SQLException
    {
        return new Journal(
                rs.getInt("id"),
                rs.getInt("student_id"),
                rs.getInt("study_plan_id"),
                rs.getBoolean("in_time"),
                rs.getInt("count"),
                rs.getInt("mark_id")
        );
    }
    RowMapper<Journal> ROW_MAPPER = (ResultSet rs, int rowNum) -> {
        return new Journal(
                rs.getInt("id"),
                rs.getInt("student_id"),
                rs.getInt("study_plan_id"),
                rs.getBoolean("in_time"),
                rs.getInt("count"),
                rs.getInt("mark_id")
        );
    };

    RowMapper<JournalFull> ROW_MAPPER_BY_STUDY_GROUP = (ResultSet rs, int rowNum) -> {
        return new JournalFull(
                rs.getInt("id"),
                rs.getInt("student_id"),
                rs.getInt("study_plan_id"),
                rs.getBoolean("in_time"),
                rs.getInt("count"),
                rs.getInt("mark_id"),
                rs.getString("name"),
                rs.getString("value"),
                rs.getInt("exam_type_id"),
                rs.getInt("subject_id"),
                rs.getString("type"),
                rs.getString("full_name"),
                rs.getString("short_name")
        );
    };
}
