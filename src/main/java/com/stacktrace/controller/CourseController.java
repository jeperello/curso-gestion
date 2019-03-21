package com.stacktrace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stacktrace.entity.Course;
import com.stacktrace.service.CourseService;

@RestController
@RequestMapping("api/courses")
public class CourseController {

	@Autowired
	CourseService courseService;
	
	/**
	 * Retrieve a course by id
	 * @param id
	 * @return return a course
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	    public Course getCourse(@PathVariable long id) {
		Course course = courseService.findById(id);
	        return course;
	    }
}
