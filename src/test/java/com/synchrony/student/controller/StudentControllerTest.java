package com.synchrony.student.controller;

import com.synchrony.student.controller.model.StudentBO;
import com.synchrony.student.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController controller;

    @Test
    public void shouldAddOrUpdateStudent() {
        StudentBO studentBO = new StudentBO();
        Mockito.when(studentService.SaveStudent(studentBO)).thenReturn(studentBO);

        ResponseEntity<StudentBO> response = controller.addOrUpdate(studentBO);

        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(response.getBody(), studentBO);
        Mockito.verify(studentService, Mockito.timeout(1)).SaveStudent(studentBO);
    }

    @Test
    public void shouldGetAllStudents() {
        StudentBO studentBO = new StudentBO();
        Mockito.when(studentService.getAllStudents()).thenReturn(List.of(studentBO));

        ResponseEntity<List<StudentBO>> response = controller.getAll();

        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(response.getBody().size(), 1);
        Mockito.verify(studentService, Mockito.timeout(1)).getAllStudents();
    }

    @Test
    public void shouldSearchStudents() {
        StudentBO studentBO = new StudentBO();
        Mockito.when(studentService.searchStudent("neha")).thenReturn(List.of(studentBO));

        ResponseEntity<List<StudentBO>> response = controller.getStudentByName("neha");

        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(response.getBody().size(), 1);
        Mockito.verify(studentService, Mockito.timeout(1)).searchStudent("neha");
    }

    @Test
    public void shouldDeleteStudent() {
        Mockito.doNothing().when(studentService).removeStudent(1);

        controller.deleteStudent("1");

        Mockito.verify(studentService, Mockito.timeout(1)).removeStudent(1);
    }
}
