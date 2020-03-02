package com.example.demo.controller;

import com.example.demo.dao.StudyGroupJdbc;
import com.example.demo.model.StudyGroup;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudyGroupController {
    private final StudyGroupJdbc studyGroupJdbc;

    public StudyGroupController(StudyGroupJdbc studyGroupJdbc){
        this.studyGroupJdbc = studyGroupJdbc;
    }

    @GetMapping("/study_group/")
    public List<StudyGroup> getStudyGroups(){
        return studyGroupJdbc.getAll();
    }
    @GetMapping("/study_group/{id}")
    public StudyGroup getStudyGroup(@PathVariable int id){
        return studyGroupJdbc.get(id);
    }

    @PostMapping("/study_group/")
    public StudyGroup createStudyGroups(@RequestBody StudyGroup studyGroup){
        return studyGroupJdbc.save(studyGroup);
    }
    @PutMapping("/study_group/")
    public StudyGroup updateStudyGroups(@RequestBody StudyGroup studyGroup){
        return studyGroupJdbc.save(studyGroup);
    }
    @DeleteMapping("/study_group/{id}")
    public int removeStudyGroup(@PathVariable int id){
        return studyGroupJdbc.delete(id);
    }


}
