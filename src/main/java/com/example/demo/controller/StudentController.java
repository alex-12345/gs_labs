package com.example.demo.controller;

import com.example.demo.dao.StudentJdbc;
import com.example.demo.model.Student;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class StudentController {
    private final StudentJdbc studentJdbc;

    public StudentController(StudentJdbc studentJdbc){
        this.studentJdbc = studentJdbc;
    }

    @GetMapping("/student/")
    public List<Student> getStudents(){
        return studentJdbc.getAll();
    }

    @GetMapping("/study_group/{study_group_id}/students")
    public List<Student> getStudentsByGroup(@PathVariable int study_group_id){
        return studentJdbc.getByStudyGroupId(study_group_id);
    }

    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable int id){
        return studentJdbc.get(id);
    }

    @PostMapping("/student/")
    public Student createStudent(@RequestBody Student student){
        return studentJdbc.save(student);
    }

    @PutMapping("/student/")
    public Student updateStudent(@RequestBody Student student){
        return studentJdbc.save(student);
    }

    @DeleteMapping("/student/{id}")
    public int removeStudent(@PathVariable int id){
        return studentJdbc.delete(id);
    }

}
