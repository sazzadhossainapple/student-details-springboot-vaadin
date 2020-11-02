package com.example.vaadinproject.repository;

import com.example.vaadinproject.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
  //  List<Student>findAllByName(String name);
 //   List<Student>findAllByNameContaining(String partialName);
  //  List<Student>findAllByDobAfter(LocalDate localDate);
}
