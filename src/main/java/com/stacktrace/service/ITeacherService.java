package com.stacktrace.service;

import java.util.List;

import com.stacktrace.entity.Teacher;

public interface ITeacherService {
	
	public List<Teacher> findAll();

	public Teacher findById(int id);
	
	public Teacher save(Teacher teacher);
}
