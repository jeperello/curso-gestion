package com.stacktrace.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stacktrace.entity.Course;
import com.stacktrace.entity.Student;
import com.stacktrace.entity.StudentCourse;
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
		return repository.findById(id).orElse(null);
	}

	@Override
	public Teacher save(Teacher teacher) {
		return repository.save(teacher);
	}

	@Override
	public void delete(Teacher teacher) {
		repository.delete(teacher);
	}
	
	@Override
	public List<Teacher> saveAll(Set<Teacher> teachers) {
		return repository.saveAll(teachers);
	}

	public Set<Course> getCoursesByTeacher(Teacher teacher) {
		Set<Course> courses = new HashSet<Course>();

		courses = teacher.getCourses();

		return courses;
	}

}
