package com.stacktrace.repository;

import org.springframework.data.repository.CrudRepository;

import com.stacktrace.entity.Course;

public interface CourseRepository extends CrudRepository<Course, Long>{
	

}
