package com.example.demo.service;

import com.example.demo.entitiy.student.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student harry = new Student(1L, "Harry", "test@test.com", LocalDate.of(2000, Month.JANUARY, 5));
            Student carry = new Student(1L, "Carry", "test@test.com", LocalDate.of(2000, Month.JANUARY, 5));
            Student larry = new Student(1L, "Larry", "test@test.com", LocalDate.of(2000, Month.JANUARY, 5));
            repository.saveAll(
                    List.of(harry, carry, larry)
            );
        };
    }
}
