package com.synchrony.student.service;

import com.synchrony.student.controller.model.StudentBO;
import com.synchrony.student.repository.StudentRepository;
import com.synchrony.student.repository.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<StudentBO> getAllStudents() {
        final List<StudentBO> students = new ArrayList<>();

        studentRepository.findAll()
                .forEach(dao -> students.add(fromDAO(dao)));

        return students;
    }

    public StudentBO SaveStudent(final StudentBO student) {
        Student studentDAO = null;
        if(!StringUtils.isEmpty(student.getId())) {
            Optional<Student> retrieved = studentRepository.findById(student.getId());

            if(retrieved.isPresent()) {
                studentDAO = retrieved.get();
                if (StringUtils.isEmpty(student.getName())) {
                    studentDAO.setName(student.getName());
                }
                if (StringUtils.isEmpty(student.getSection())) {
                    studentDAO.setSection(student.getSection());
                }
                if (StringUtils.isEmpty(student.getAge())) {
                    studentDAO.setAge(student.getAge());
                }
                if (StringUtils.isEmpty(student.getPhone())) {
                    studentDAO.setPhone(student.getPhone());
                }
            }
        } else {
            studentDAO = toDAO(student);
        }

        final Student updated = studentRepository.save(studentDAO);
        return fromDAO(updated);
    }

    public void removeStudent(final Integer studentId) {
        studentRepository.deleteById(studentId);
    }

    public List<StudentBO> searchStudent(final String name) {
        List<Student> retrieved = studentRepository.findByName(name);

        return retrieved.stream()
                .map(dao -> fromDAO(dao))
                .collect(Collectors.toList());
    }

    private StudentBO fromDAO(final Student student) {
        final StudentBO businessObj = new StudentBO();

        businessObj.setName(student.getName());
        businessObj.setId(student.getId());
        businessObj.setAge(student.getAge());
        businessObj.setPhone(student.getPhone());
        businessObj.setSection(student.getSection());

        return businessObj;
    }

    private Student toDAO(final StudentBO businessObj) {
        final Student student = new Student();

        student.setName(businessObj.getName());
        student.setId(businessObj.getId());
        student.setAge(businessObj.getAge());
        student.setPhone(businessObj.getPhone());
        student.setSection(businessObj.getSection());

        return student;
    }
}
