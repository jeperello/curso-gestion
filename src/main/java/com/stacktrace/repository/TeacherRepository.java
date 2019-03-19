package com.stacktrace.repository;

import org.springframework.data.repository.CrudRepository;

import com.stacktrace.entity.Teacher;


public interface TeacherRepository extends CrudRepository<Teacher, Long>{
	

}
