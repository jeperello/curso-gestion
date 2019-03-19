package com.stacktrace.repository;

import org.springframework.data.repository.CrudRepository;

import com.stacktrace.entity.Student;


public interface StudentRepository extends CrudRepository<Student, Long>{
	

}
