package org.studentmanagement.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.studentmanagement.model.Student;
import org.studentmanagement.service.StudentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system")
public class StudentController {

    @Autowired
    StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }


    @GetMapping("/students")
    public List<Student> ShowStudents() {
        return service.ShowStudents();
    }

    @PostMapping("/students/add")
    public String addStudent(@RequestBody Student student){
        service.addStudent(student);
        return "Student added Successfully!";
    }

    @DeleteMapping("/students/{registernumber}")
    public String deleteStudent(@PathVariable long registernumber){
        service.deleteStudent(registernumber);
        return "Successfully deleted!";
    }

    @GetMapping("/students/{registernumber}")
    public Student getStudent(@PathVariable long registernumber){
        return service.getStudent(registernumber);
    }

    @PutMapping("/students/{registernumber}")
    public ResponseEntity<?> updateStudent(
            @PathVariable long registernumber,
            @RequestBody Student student) {

        Student updatedStudent = service.updateStudent(registernumber, student);

        if (updatedStudent != null) {
            return ResponseEntity.ok(updatedStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/students/{registernumber}")
    public ResponseEntity<?> patchStudent(
            @PathVariable long registernumber,
            @RequestBody Map<String, Object> updates) {

        Student patchedStudent = service.patchStudent(registernumber, updates);

        if (patchedStudent != null) {
            return ResponseEntity.ok(patchedStudent); // 200 OK
        } else {
            return ResponseEntity.notFound().build(); // 404 if student not found
        }
    }



}
