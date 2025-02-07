package com.synchrony.student.controller;

import com.synchrony.student.controller.model.StudentBO;
import com.synchrony.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/api/v1/student")
public class StudentController {
	@Autowired
	private StudentService studentService;

	@PostMapping
	public ResponseEntity<StudentBO> addOrUpdate(@RequestBody final StudentBO student) {
		final StudentBO inserted = studentService.SaveStudent(student);
		return ResponseEntity.ok(inserted);
	}

	@GetMapping
	public ResponseEntity<List<StudentBO>> getAll() {
		final List<StudentBO> allStudents = studentService.getAllStudents();
		return ResponseEntity.ok(allStudents);
	}

	@GetMapping(path="/search/{name}")
	public ResponseEntity<List<StudentBO>> getStudentByName(@PathVariable final String name) {
		final List<StudentBO> matchingStudents = studentService.searchStudent(name);
		return ResponseEntity.ok(matchingStudents);
	}

	@DeleteMapping(path="/{id}")
	public ResponseEntity deleteStudent(@PathVariable final String id) {
		studentService.removeStudent(Integer.valueOf(id));

		return ResponseEntity.accepted().build();
	}
}
