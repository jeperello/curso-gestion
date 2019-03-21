package com.stacktrace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stacktrace.entity.Teacher;
import com.stacktrace.repository.TeacherRepository;

@Service
public class TeacherService implements ITeacherService {

	@Autowired
	TeacherRepository repository;
	
	@Override
	public List<Teacher> findAll() {
		List<Teacher> teachers = (List<Teacher>) repository.findAll();
		return teachers;
	}
	
	@Override
	public Teacher findById(int id) {
		Teacher teacher = new Teacher();
		teacher = repository.findById((long) id).orElse(null);
		return teacher;
	}

	@Override
	public Teacher save(Teacher teacher) {
		return repository.save(teacher);
	}

}
