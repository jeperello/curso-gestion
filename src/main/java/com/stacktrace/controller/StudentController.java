package com.stacktrace.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stacktrace.entity.Course;
import com.stacktrace.entity.Student;
import com.stacktrace.entity.Teacher;
import com.stacktrace.service.StudentService;

@RestController
@RequestMapping("api/students")
public class StudentController {

	@Autowired
	StudentService studentService;

	/**
	 * Retrieve all students.
	 * 
	 * @return a List of students
	 */
	@GetMapping
	public List<Student> getAllStudents() {
		List<Student> students = studentService.findAll();
		return students;
	}

	/**
	 * Retrieve student by id.
	 * 
	 * @param id
	 * @return a student
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getStudentById(@PathVariable("id") Long id) {
		Student student = studentService.findById(id);
		if (student != null) {
			return new ResponseEntity<>(student, HttpStatus.OK);
		}
		return new ResponseEntity<>(Collections.singletonMap("id", id), HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Save a student.
	 * 
	 * @param student
	 * @return return saved student
	 */
	@PostMapping
	public ResponseEntity<Map<String, Object>> saveStudent(@RequestBody @Valid Student student) {
		try {
			Student studentSaved = studentService.save(student);
			return new ResponseEntity<>(Collections.singletonMap("id", studentSaved.getId()), HttpStatus.CREATED);
		} catch (Exception ex) {
			return new ResponseEntity<>(Collections.singletonMap("error", ex.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Update a student
	 * 
	 * @param student
	 * @return return updated student
	 */
	@PutMapping
	public ResponseEntity<Map<String, Object>> Student(@RequestBody Student student) {
		try {
			Student studentSaved = studentService.findById(student.getId());
			if (studentSaved != null) {
				studentService.save(student);
				return new ResponseEntity<>(Collections.singletonMap("id", student.getId()), HttpStatus.CREATED);
			}
			return new ResponseEntity<>(Collections.singletonMap("id", student.getId()), HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			return new ResponseEntity<>(Collections.singletonMap("error", ex.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Delete a student
	 * 
	 * @param studentId
	 * @return return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable(value = "id") Long id) {
		Student studentSaved = studentService.findById(id);
		if (studentSaved != null) {
			studentService.delete(studentSaved);
			return ResponseEntity.ok().build();
		}
		return new ResponseEntity<>(Collections.singletonMap("id", id), HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Retrieve a list of course by studentId.
	 * 
	 * @param id
	 * @return a List of courses
	 */
	@GetMapping(value = "/{id}/courses")
	public ResponseEntity<?> getStudentsByCourseId(@PathVariable("id") Long id) {
			
		Student studentSaved = studentService.findById(id);
		if (studentSaved != null) {
			Set<Course> courses = studentService.getCoursesByStudent(studentSaved);

			return new ResponseEntity<>(courses, HttpStatus.OK);
		}
		return new ResponseEntity<>(Collections.singletonMap("id", id), HttpStatus.NOT_FOUND);
	}

	
}
