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
import com.stacktrace.entity.Title;
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

	@Override
	public List<Student> saveAll(Set<Student> students) {
		return repository.saveAll(students);
	}
	
	public Set<Course> getCoursesByStudent(Student student) {
		Set<Course> courses = new HashSet<Course>();

		Set<StudentCourse> studentsCourse = student.getStudentCourses();
		for (StudentCourse studentCourse : studentsCourse)
			courses.add(studentCourse.getCourse());

		return courses;
	}

	public Set<Course> getApprovedCoursesByStudent(Student student) {
		Set<Course> courses = new HashSet<Course>();

		Set<StudentCourse> studentsCourse = student.getStudentCourses();
		for (StudentCourse studentCourse : studentsCourse) {	
			Course currentCourse = studentCourse.getCourse();
			if(currentCourse.getApproveGrade() <= studentCourse.getGrade()) {
				courses.add(currentCourse);
			}
		}
		return courses;
	}

}
