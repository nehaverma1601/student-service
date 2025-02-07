package com.synchrony.student.service;

import com.synchrony.student.controller.model.StudentBO;
import com.synchrony.student.repository.StudentRepository;
import com.synchrony.student.repository.entity.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    public void shouldAddStudent() {
        StudentBO studentBO = new StudentBO();
        studentBO.setName("Neha");
        Student student = new Student();
        student.setName("Neha");
        Mockito.when(studentRepository.save(any(Student.class))).thenReturn(student);

        StudentBO inserted = studentService.SaveStudent(studentBO);

        Assertions.assertNotNull(inserted);
        Assertions.assertEquals("Neha", inserted.getName());
    }

    @Test
    public void shouldUpdateStudent() {
        StudentBO studentBO = new StudentBO();
        studentBO.setId(1);
        studentBO.setName("Neha");
        Student student = new Student();
        student.setName("Neha");

        Mockito.when(studentRepository.save(any(Student.class))).thenReturn(student);
        Mockito.when(studentRepository.findById(1)).thenReturn(Optional.of(student));

        StudentBO inserted = studentService.SaveStudent(studentBO);

        Assertions.assertNotNull(inserted);
        Assertions.assertEquals("Neha", inserted.getName());
    }

    @Test
    public void shouldGetAllStudents() {
        StudentBO studentBO = new StudentBO();
        studentBO.setId(1);
        studentBO.setName("Neha");
        Student student = new Student();
        student.setName("Neha");

        Mockito.when(studentRepository.findAll()).thenReturn(List.of(student));

        List<StudentBO> allStudents = studentService.getAllStudents();

        Assertions.assertNotNull(allStudents);
        Assertions.assertEquals(1, allStudents.size());
    }

    @Test
    public void shouldSearchStudentsByName() {

        Student student = new Student();
        student.setName("Neha");

        Mockito.when(studentRepository.findByName("Neha")).thenReturn(List.of(student));

        List<StudentBO> matchingStudents = studentService.searchStudent("Neha");

        Assertions.assertNotNull(matchingStudents);
        Assertions.assertEquals(1, matchingStudents.size());
    }

    @Test
    public void shouldRemoveStudentById() {

        Mockito.doNothing().when(studentRepository).deleteById(1);

        studentService.removeStudent(1);

        Mockito.verify(studentRepository, Mockito.times(1)).deleteById(1);
    }
}
