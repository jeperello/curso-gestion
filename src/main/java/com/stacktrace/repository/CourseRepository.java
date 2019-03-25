package com.stacktrace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stacktrace.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{

}
