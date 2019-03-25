package com.stacktrace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stacktrace.entity.Course;
import com.stacktrace.entity.Teacher;
import com.stacktrace.repository.CourseRepository;

@Service
public class CourseService implements ICourseService {

	@Autowired
	CourseRepository repository;
	
	@Override
	public List<Course> findAll() {
		List <Course> courses = (List<Course>) repository.findAll();
		return courses;
	}

	@Override
	public Course findById(long id) {
		Course course = repository.findById(id).orElse(null);
		return course;
	}

	@Override
	public Course save(Course course) {
		return repository.save(course);
	}
	
	@Override
	public void delete(Course course) {
		repository.delete(course);
	}

}
