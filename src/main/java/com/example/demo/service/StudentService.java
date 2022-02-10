package com.example.demo.service;

import com.example.demo.entitiy.student.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudents(Student student) throws Exception {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if (studentOptional.isPresent()) {
            throw new Exception("Email is taken!");
        }

        studentRepository.save(student);
    }

    public void deleteStudent(Long id) throws Exception {
        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            throw new Exception("Student with " + id + " does not exist!");
        }

        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long id, String name, String email) throws Exception {
        Student student = studentRepository.findById(id).orElseThrow(() -> new Exception("Student with " + id + " does not exist!"));

        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new Exception("Email taken!");
            }

            student.setEmail(email);
        }
    }
}
