package com.stacktrace.service;

import java.util.List;
import java.util.Set;

import com.stacktrace.entity.Student;

/**
 * @author jorge
 *
 */
public interface IStudentService {

	public List<Student> findAll();
	
	public Student findById(long id);
	
	public Student save(Student student);

	void delete(Student student);

	List<Student> saveAll(Set<Student> students);
}
