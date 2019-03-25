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
	public Teacher findById(Long id) {
		//Teacher teacher = new Teacher();
		return repository.findById(id).orElse(null);
		//return teacher;
	}

	@Override
	public Teacher save(Teacher teacher) {
		return repository.save(teacher);
	}
	
	@Override
	public void delete(Teacher teacher) {
		repository.delete(teacher);
	}

}
