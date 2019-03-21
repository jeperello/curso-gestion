package com.stacktrace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stacktrace.entity.Student;
import com.stacktrace.service.StudentService;

@RestController
@RequestMapping("api/students")
public class StudentController {

	@Autowired
	StudentService studentService;
	
	/**
	 * Retrieve student by id
	 * @param id
	 * @return return a student
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	    public Student getStudent(@PathVariable long id) {
		Student student = studentService.findById(id);
	        return student;
	    }
}
