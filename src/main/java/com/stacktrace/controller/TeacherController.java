package com.stacktrace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stacktrace.entity.Teacher;
import com.stacktrace.service.TeacherService;

@RestController
@RequestMapping("api/teachers")
public class TeacherController {

	@Autowired
	TeacherService teacherService;
	
	/**
	 * Retrieve teacher by id
	 * @param id
	 * @return a teacher
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	    public Teacher getTeacher(@PathVariable int id) {
		Teacher teacher = teacherService.findById(id);
	        return teacher;
	    }
}
