package com.stacktrace.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.stacktrace.entity.Course;
import com.stacktrace.entity.Student;
import com.stacktrace.entity.StudentCourse;
import com.stacktrace.entity.Teacher;
import com.stacktrace.repository.CourseRepository;

@Service
public class CourseService implements ICourseService {

	@Autowired
	CourseRepository repository;

	@Override
	public List<Course> findAll() {
		List<Course> courses = (List<Course>) repository.findAll();
		return courses;
	}

	@Override
	public Course findById(long id) {
		Course course = repository.findById(id).orElse(null);
		return course;
	}

	@Override
	public Course save(Course course) {
		return repository.save(course);
	}

	@Override
	public void delete(Course course) {
		repository.delete(course);
	}

	@Override
	public List<Course> saveAll(Set<Course> courses) {
		return repository.saveAll(courses);
	}
	
	public Set<Student> getStudentsByCourse(Course course) {
		Set<Student> students = new HashSet<Student>();
		Set<StudentCourse> studentsCourse = course.getStudentCourses();
		
		for (StudentCourse studentCourse : studentsCourse)
			students.add(studentCourse.getStudent());

		return students;
	}

	public Set<Student> getApprovedStudentsByCourse(Course course) {
		Set<Student> students = new HashSet<Student>();
		Set<StudentCourse> studentsCourse = course.getStudentCourses();
		
		for (StudentCourse studentCourse : studentsCourse)
			students.add(studentCourse.getStudent());

		return students;
	}

}
