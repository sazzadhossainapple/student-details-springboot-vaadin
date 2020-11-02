package com.example.springbootdemoproject3.repository;
import com.example.springbootdemoproject3.model.Student;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
@RunWith(SpringRunner.class)
@SpringBootTest

public class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;
    @Test
    public void  testCreatedStudent(){
        Student student = new Student(1238l,"Sazzad Hossain", LocalDate.now());
        Student savedStudent = studentRepository.save(student);
        assertEquals(student,savedStudent);
    }

}
