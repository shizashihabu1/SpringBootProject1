package com.example.studentapi.controller;

import com.example.studentapi.model.Student;
import com.example.studentapi.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository repo;

    public StudentController(StudentRepository repo) {
        this.repo = repo;
    }

    // GET all students
    @GetMapping
    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    // GET student by ID
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    // CREATE new student
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return repo.save(student);
    }

    // UPDATE existing student
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student updated) {
        Student student = repo.findById(id).orElseThrow();
        student.setName(updated.getName());
        student.setEmail(updated.getEmail());
        student.setAge(updated.getAge());
        return repo.save(student);
    }

    // DELETE student
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        repo.deleteById(id);
    }
}