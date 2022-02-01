package com.example.demo.service;

import com.example.demo.entitiy.student.Student;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @GetMapping
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<Student>();

        for (long i = 0; i < 5; i++) {
            Student newStudent = new Student(i, "Harry" + i, "test" + i + "@test.com", LocalDate.of(2000, Month.JANUARY, 5), 21 + (int) i);
            students.add(newStudent);
        }

        return students;
    }
}
