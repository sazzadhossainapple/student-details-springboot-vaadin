package com.example.springbootdemoproject3.service;
import com.example.springbootdemoproject3.exception.ResourceAlreadyExistException;
import com.example.springbootdemoproject3.model.Student;
import com.example.springbootdemoproject3.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertEquals;
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void deleteAll(){
        studentRepository.deleteAll();
    }


    @Test
    public void  testInsertStudent(){
        Student student = new Student(1234,"khalid",LocalDate.of(2000,Month.JANUARY,12));
        try {
            Student insertStudent = studentService.insertStudent(student);
            assertEquals(student,insertStudent);
        } catch (ResourceAlreadyExistException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = ResourceAlreadyExistException.class)
    public void  testInsertExitingStudent() throws ResourceAlreadyExistException{
        Student student1 = new Student(1234,"khalid",LocalDate.of(2000,Month.JANUARY,12));
        Student student2 = new Student(1234,"apple",LocalDate.of(2001,Month.JANUARY,12));

        try {
            Student insertStudent = studentService.insertStudent(student1);
            assertEquals(student1,insertStudent);
        } catch (ResourceAlreadyExistException e) {
            e.printStackTrace();
        }
        try {
            Student insertStudent = studentService.insertStudent(student2);
            assertEquals(student2,insertStudent);
        } catch (ResourceAlreadyExistException e) {
            throw e;
        }
    }

}
