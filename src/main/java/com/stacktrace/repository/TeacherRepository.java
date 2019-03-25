package com.stacktrace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stacktrace.entity.Teacher;


public interface TeacherRepository extends JpaRepository<Teacher, Long>{
	

}
