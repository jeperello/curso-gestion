package com.stacktrace.service;

import java.util.List;
import java.util.Set;

import com.stacktrace.entity.Teacher;

public interface ITeacherService {
	
	public List<Teacher> findAll();

	public Teacher findById(Long id);
	
	public Teacher save(Teacher teacher);

	void delete(Teacher teacher);

	List<Teacher> saveAll(Set<Teacher> teachers);
}
