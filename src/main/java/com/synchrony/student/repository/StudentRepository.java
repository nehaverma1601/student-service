package com.synchrony.student.repository;

import com.synchrony.student.repository.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

    List<Student> findByName(final String name);
}
