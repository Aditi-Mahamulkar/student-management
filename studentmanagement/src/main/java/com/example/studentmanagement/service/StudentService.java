package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    // GET ALL STUDENTS
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    // ADD SINGLE STUDENT
    public Student saveStudent(Student student) {
        return repository.save(student);
    }

    // ✅ BULK INSERT (FIXED)
    public List<Student> saveAllStudents(List<Student> students) {
        return repository.saveAll(students);
    }

    // GET STUDENT BY ID
    public Student getStudentById(Long id) {
        return repository.findById(id ).orElse(null);
    }

    // UPDATE STUDENT (FIXED — REMOVED COURSE)
    public Student updateStudent(Long id, Student student) {
        Student existing = repository.findById(id).orElse(null);

        if (existing != null) {
            existing.setName(student.getName());
            existing.setAge(student.getAge());      // ✅ FIXED
            existing.setEmail(student.getEmail());
            return repository.save(existing);
        }

        return null;
    }


    // DELETE STUDENT
    public void deleteMultipleStudents(List<Long> ids) {
        repository.deleteAllById(ids);
    }

}
