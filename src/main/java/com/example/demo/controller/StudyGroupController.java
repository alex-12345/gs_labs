package com.example.demo.controller;

import com.example.demo.dao.StudyGroupJdbc;
import com.example.demo.model.StudyGroup;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
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
