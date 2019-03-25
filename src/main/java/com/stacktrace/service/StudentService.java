package com.stacktrace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stacktrace.entity.Student;
import com.stacktrace.entity.Teacher;
import com.stacktrace.repository.StudentRepository;

@Service
public class StudentService implements IStudentService {

	@Autowired
	StudentRepository repository;
	
	@Override
	public List<Student> findAll() {
		List<Student> students = (List<Student>) repository.findAll();
		return students;
	}
	
	@Override
	public Student findById(long id) {
		Student student = new Student();
		student = repository.findById(id).orElse(null);
		return student;
	}

	@Override
	public Student save(Student student) {
		return repository.save(student);
	}
	
	@Override
	public void delete(Student student) {
		repository.delete(student);
	}


}
