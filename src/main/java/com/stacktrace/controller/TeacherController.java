package com.stacktrace.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RestController;

import com.stacktrace.entity.Teacher;
import com.stacktrace.service.TeacherService;

@RestController
@RequestMapping(TeacherController.TEACHER_RESOURCE)
public class TeacherController {

	public static final String TEACHER_RESOURCE = "/api/teachers";

	@Autowired
	TeacherService teacherService;

	/**
	 * Retrieve all teachers.
	 * 
	 * @return a List of teacher
	 */
	@GetMapping
	public List<Teacher> getAllTeacher() {
		List<Teacher> teacher = teacherService.findAll();
		return teacher;
	}

	/**
	 * Retrieve a teacher by id.
	 * 
	 * @param id
	 * @return a teacher
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getTeacherById(@PathVariable("id") Long id) {
		Teacher teacher = teacherService.findById(id);
		if (teacher != null) {
			return new ResponseEntity<>(teacher, HttpStatus.OK);
		}
		return new ResponseEntity<>(Collections.singletonMap("id", id), HttpStatus.NOT_FOUND);
	}

	/**
	 * Save a teacher.
	 * 
	 * @param teacher
	 * @return return saved teacher
	 */
	@PostMapping
	public ResponseEntity<Map<String, Object>> saveTeacher(@RequestBody @Valid Teacher teacher) {
		try {
			Teacher teacherSaved = teacherService.save(teacher);
			return new ResponseEntity<>(Collections.singletonMap("id", teacherSaved.getId()), HttpStatus.CREATED);
		} catch (Exception ex) {
			return new ResponseEntity<>(Collections.singletonMap("error", ex.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Update a teacher
	 * 
	 * @param teacher
	 * @return return updated teacher
	 */
	@PutMapping
	public ResponseEntity<Map<String, Object>> updateTeacher(@RequestBody @Valid Teacher teacher) {
		try {
			Teacher teacherUpdate = teacherService.findById(teacher.getId());
			if (teacherUpdate != null) {
				teacherService.save(teacherUpdate);
				return new ResponseEntity<>(Collections.singletonMap("id", teacherUpdate.getId()), HttpStatus.CREATED);
			}
			//return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(Collections.singletonMap("id", teacher.getId()), HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			return new ResponseEntity<>(Collections.singletonMap("error", ex.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Delete a teacher
	 * 
	 * @param teacherId
	 * @return return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTeacher(@PathVariable(value = "id") Long id) {
		Teacher teacher = teacherService.findById(id);
		if (teacher != null) {
			teacherService.delete(teacher);
			return ResponseEntity.ok().build();
		}
		return new ResponseEntity<>(Collections.singletonMap("id", id), HttpStatus.NOT_FOUND);
	}
}
