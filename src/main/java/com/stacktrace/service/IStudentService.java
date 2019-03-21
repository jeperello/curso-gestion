package com.stacktrace.service;

import java.util.List;

import com.stacktrace.entity.Student;

/**
 * @author jorge
 *
 */
public interface IStudentService {

	public List<Student> findAll();
	
	public Student findById(long id);
}