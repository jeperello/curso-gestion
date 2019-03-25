package com.stacktrace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stacktrace.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
	

}
