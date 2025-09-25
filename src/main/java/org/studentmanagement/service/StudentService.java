package org.studentmanagement.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.studentmanagement.model.Student;
import org.studentmanagement.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    private final List<Student> students= new ArrayList<>();

    public List<Student> ShowStudents() {
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }


    public void deleteStudent(long registernumber) {
        studentRepository.deleteById(registernumber);
    }

    public Student getStudent(long registernumber) {
        return studentRepository.findById(registernumber).orElse(null);
    }

    public Student updateStudent(long registernumber, Student updatedStudent) {
        return studentRepository.findById(registernumber)
                .map(student -> {
                    student.setName(updatedStudent.getName());
                    student.setEmail(updatedStudent.getEmail());
                    student.setDegree(updatedStudent.getDegree());
                    student.setDepartment(updatedStudent.getDepartment());
                    student.setPhonenumber(updatedStudent.getPhonenumber());
                    student.setAge(updatedStudent.getAge());
                    return studentRepository.save(student); // save updated record
                })
                .orElse(null);
    }

    public Student patchStudent(long registernumber, Map<String, Object> updates) {
        return studentRepository.findById(registernumber)
                .map(student -> {
                    updates.forEach((key, value) -> {
                        switch (key) {
                            case "name" -> student.setName((String) value);
                            case "email" -> student.setEmail((String) value);
                            case "degree" -> student.setDegree((String) value);
                            case "department" -> student.setDepartment((String) value);
                            case "phonenumber" -> student.setPhonenumber((String) value);
                            case "age" -> student.setAge(Integer.parseInt(value.toString()));
                        }
                    });
                    return studentRepository.save(student); // save updated student
                })
                .orElse(null);
    }


}
