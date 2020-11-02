package com.example.vaadinproject.service;

import com.example.vaadinproject.Service.StudentService;
import com.example.vaadinproject.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void testFindAll(){
        List<Student> studentList = studentService.findAll();
        assertNotNull(studentList);
        studentList.forEach(System.out::println);

    }


    @Test
    public void testFindById(){
        Student expectedStudent = new Student(2017000000345l,"sazzad hossain", LocalDate.of(1997, Month.APRIL,27));
        Student actualById= studentService.findById(2017000000345l);
       assertEquals(expectedStudent,actualById);

    }


}
