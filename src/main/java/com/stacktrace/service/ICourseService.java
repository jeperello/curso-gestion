package com.stacktrace.service;

import java.util.List;

import com.stacktrace.entity.Course;

/**
 * @author jorge
 *
 */
public interface ICourseService {

	public List<Course> findAll();
	
	public Course findById(long id);
	
	public Course save(Course course);

	void delete(Course course);
}
