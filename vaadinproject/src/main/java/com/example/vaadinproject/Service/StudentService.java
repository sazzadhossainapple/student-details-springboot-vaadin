package com.example.vaadinproject.Service;

import com.example.vaadinproject.model.Student;
import com.example.vaadinproject.repository.StudentRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class StudentService implements GenericService<Student,Long>{
    private StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;

    }



  public Student findById(Long id)
  {
      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<Student>response = restTemplate
              .exchange(
                      "http://javaspringboot-demo.herokuapp.com/api/v1/students" + "/" + id,
                      HttpMethod.GET,
                      null,
                      Student.class);
      Student student=response.getBody();
      return student;

  }

  public List<Student> findAll(){
        //return studentRepository.findAll();
      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<List<Student>> response = restTemplate
              .exchange(
                      "http://javaspringboot-demo.herokuapp.com/api/v1/students",
                      HttpMethod.GET,
                      null,
                      new ParameterizedTypeReference<List<Student>>() {
                      }
              );
      List<Student>studentList=response.getBody();
      return studentList;

  }
  public Student save(Student student){
      RestTemplate restTemplate = new RestTemplate();
      HttpEntity<Student>studentHttpEntity =new HttpEntity<>(student);
      ResponseEntity<Student> studentResponseEntity = restTemplate
              .exchange(
                      "http://javaspringboot-demo.herokuapp.com/api/v1/students",
                      HttpMethod.POST,
                      studentHttpEntity,
                      Student.class);

        return studentResponseEntity.getBody();
  }

  public void delete (Student student){
        studentRepository.delete(student);
  }
}
