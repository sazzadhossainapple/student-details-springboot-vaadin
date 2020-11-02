package com.example.springbootdemoproject3.service;
import com.example.springbootdemoproject3.exception.ResourceAlreadyExistException;
import com.example.springbootdemoproject3.exception.ResourceDoseNotExistException;
import com.example.springbootdemoproject3.model.Student;
import com.example.springbootdemoproject3.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public  Student findById(long id) throws ResourceDoseNotExistException {

        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(optionalStudent.isPresent())
        {
            return optionalStudent.get();
        }
        else {
            throw new ResourceDoseNotExistException(id + "");
        }
    }


    public List<Student> findAll() {
        List<Student>studentList = new ArrayList<>();
        studentRepository.findAll().forEach(studentList :: add);
        return studentList;
    }


    public boolean deleteById(long id) throws ResourceDoseNotExistException{

        Optional<Student> optionalStudent = studentRepository.findById(id);
        optionalStudent.ifPresent(student -> studentRepository.deleteById(id));
        optionalStudent.orElseThrow(() -> new ResourceDoseNotExistException(id + ""));

        //return optionalStudent.isPresent();
        return true;

    }

    public Student insertStudent(Student student) throws ResourceAlreadyExistException {
        Optional<Student> optionalStudent = studentRepository.findById(student.getId());
        if (optionalStudent.isPresent()) {
            throw  new ResourceAlreadyExistException(student.getId() + "");
        } else {
            return studentRepository.save(student);
        }

    }

    public Student updateStudent(long id ,Student student) throws ResourceDoseNotExistException {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {

            student.setId(id);
            return studentRepository.save(student);

        } else {
            throw  new ResourceDoseNotExistException(id+ "");
        }
    }

}
