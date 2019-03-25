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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stacktrace.entity.Course;
import com.stacktrace.entity.Teacher;
import com.stacktrace.service.CourseService;

@RestController
@RequestMapping("api/courses")
public class CourseController {

	@Autowired
	CourseService courseService;

	/**
	 * Retrieve all course.
	 * 
	 * @return a List of courses
	 */
	@GetMapping
	public List<Course> getAllCourses() {
		List<Course> courses = courseService.findAll();
		return courses;
	}

	/**
	 * Retrieve a course by id.
	 * 
	 * @param id
	 * @return a course
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getCourseById(@PathVariable("id") Long id) {
		Course course = courseService.findById(id);
		if (course != null) {
			return new ResponseEntity<>(course, HttpStatus.OK);
		}
		return new ResponseEntity<>(Collections.singletonMap("id", id), HttpStatus.NOT_FOUND);
	}

	/**
	 * Save a course.
	 * 
	 * @param course
	 * @return return saved course
	 */
	@PostMapping
	public ResponseEntity<Map<String, Object>> saveCourse(@RequestBody @Valid Course course) {
		try {
			Course courseSaved = courseService.save(course);
			return new ResponseEntity<>(Collections.singletonMap("id", courseSaved.getId()), HttpStatus.CREATED);
		} catch (Exception ex) {
			return new ResponseEntity<>(Collections.singletonMap("error", ex.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Update a course
	 * 
	 * @param course
	 * @return return updated course
	 */
	@PutMapping
	public ResponseEntity<Map<String, Object>> updateTeacher(@RequestBody @Valid Course course) {
		try {
			Course courseSaved = courseService.findById(course.getId());
			if (courseSaved != null) {
				courseService.save(course);
				return new ResponseEntity<>(Collections.singletonMap("id", course.getId()), HttpStatus.CREATED);
			}
			return new ResponseEntity<>(Collections.singletonMap("id", course.getId()), HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			return new ResponseEntity<>(Collections.singletonMap("error", ex.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Delete a course
	 * 
	 * @param courseId
	 * @return return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCourse(@PathVariable(value = "id") Long id) {
		Course course = courseService.findById(id);
		if (course != null) {
			courseService.delete(course);
			return ResponseEntity.ok().build();
		}
		return new ResponseEntity<>(Collections.singletonMap("id", id), HttpStatus.NOT_FOUND);
	}
}
