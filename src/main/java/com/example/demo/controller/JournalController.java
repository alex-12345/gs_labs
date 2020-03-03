package com.example.demo.controller;

import com.example.demo.dao.JournalJdbc;
import com.example.demo.model.Journal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JournalController {
    private final JournalJdbc journalJdbc;

    public JournalController(JournalJdbc journalJdbc){
        this.journalJdbc = journalJdbc;
    }

    @GetMapping("/student/{student_id}/journal")
    public List<Journal> getJournalByStudent(@PathVariable int student_id){
        return journalJdbc.getAllByStudent(student_id);
    }

    @GetMapping("/study_group/{study_group_id}/journal")
    public List<Journal> getJournalByStudyGroup(@PathVariable int study_group_id){
        return journalJdbc.getAllByStudyGroup(study_group_id);
    }

    @GetMapping("/journal/{id}")
    public Journal getJournal(@PathVariable int id){
        return journalJdbc.get(id);
    }

    @PostMapping("/journal/")
    public Journal createJournal(@RequestBody Journal journal){
        return journalJdbc.save(journal);
    }

    @PutMapping("/journal/")
    public Journal updateJournal(@RequestBody Journal journal){
        return journalJdbc.save(journal);
    }

    @DeleteMapping("/journal/{id}")
    public int removeJournal(@PathVariable int id){
        return journalJdbc.delete(id);
    }
}
