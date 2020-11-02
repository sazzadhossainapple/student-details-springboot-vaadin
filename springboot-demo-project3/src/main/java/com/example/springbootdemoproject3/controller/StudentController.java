package com.example.springbootdemoproject3.controller;

import com.example.springbootdemoproject3.exception.ResourceAlreadyExistException;
import com.example.springbootdemoproject3.exception.ResourceDoseNotExistException;
import com.example.springbootdemoproject3.model.Student;
import com.example.springbootdemoproject3.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping(value = "/student")
@RequestMapping(value = "/api/v1/students")
//192.168.0.105
public class StudentController {



    /*
        @Autowired
        private StudentRepository studentRepository;
        private Map<Long,Student> studentMap;

        public StudentController()
       {
           studentMap = new HashMap<>();
           studentMap.put(1234l,new Student(1234,"jone deo",LocalDate.of(2000, Month.APRIL,12)));
           studentMap.put(1235l,new Student(1235,"jone deo",LocalDate.of(2000, Month.APRIL,12)));

       }

        */
    private StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    //GET http//:192.168.0.105:8081/api/v1/students/1234
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable long id)
    {

        try {
            Student student =studentService.findById(id);
            return ResponseEntity.ok(student);
        } catch (ResourceDoseNotExistException e) {
            return ResponseEntity.notFound().build();
        }catch (Exception e) {
            //return studentRepository.findById(id).get();
            return ResponseEntity.badRequest().build();
        }
    }
/*
    @GetMapping("/findByName/{name}")
    public List<Student>  getStudents(@PathVariable String name)
    {
        return studentRepository.findAllByName(name);

    }

*/

    @GetMapping("/findByName/{name}")
    public List<Student> getStudents(@PathVariable String name)
    {
        // return studentRepository.findAllByNameContaining(name);
        return null;

    }
    //GET http//:192.168.0.105:8081/api/v1/students
    @GetMapping("")
    public ResponseEntity<List<Student> >getStudents()
    {
        //List<Student>studentList = new ArrayList<>();
        //studentRepository.findAll().forEach(studentList::add);
        //return studentList;
        List<Student> studentList = studentService.findAll();
        return ResponseEntity.ok(studentList);

//        try {
//           // studentList = studentMap.values().stream().collect(Collectors.toList());
//            return ResponseEntity.ok(studentList);
//        }catch (Exception e)
//        {
//            return ResponseEntity.badRequest().body(studentList);
//        }
    }
    /*
     @GetMapping("/insert/{id}/{name}")
     public Student insertStudent(@PathVariable long id, @PathVariable String name)
     {
         Student student = new Student(id,name, LocalDate.now());
         return studentRepository.save(student);

     }

     */
    //POST http//:192.168.0.105:8081/api/v1/students
    @PostMapping("")
    public ResponseEntity<Student> insertStudent(@RequestBody Student student)
    {
        try {
            Student insertStudent = studentService.insertStudent(student);
            return ResponseEntity.status(HttpStatus.CREATED).body(insertStudent);
        } catch (ResourceAlreadyExistException e) {
            return ResponseEntity.badRequest().body(null);
        }

        //return studentRepository.save(student);
    }


    //POST http//:192.168.0.105:8081/api/v1/students
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteStudent(@PathVariable long id) {
        try {
            boolean deleted = studentService.deleteById(id);
            return ResponseEntity.ok(id);
        } catch (ResourceDoseNotExistException e) {
            return ResponseEntity.notFound().build();
        }
//        boolean deleted = studentService.deleteById(id);
//       // return deleted? ResponseEntity.ok(id):ResponseEntity.notFound().build();
//        if(deleted)
//            return ResponseEntity.ok(id);
//        else
//            return ResponseEntity.notFound().build();

        //return studentRepository.save(student);
    }
}
