package com.stacktrace.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Student")
public class Student extends Person {
	
	/**
	 * Courses
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	Set<CourseStudent> courseStudents = new HashSet<CourseStudent>();

	/**
	 * Constructors
	 */
	public Student() {
	}

	public Student(String name, String lastName) {
		super.setName(name);
		super.setLastName(lastName);
	}

	/**
	 * @return the courseStudents
	 */
	public Set<CourseStudent> getCourseStudents() {
		return courseStudents;
	}

	/**
	 * @param courseStudents the courseStudents to set
	 */
	public void setCourseStudents(Set<CourseStudent> courseStudents) {
		this.courseStudents = courseStudents;
	}
	
	public Student(String name, String lastName, CourseStudent... courseStudents) {
		super.setName(name);
		super.setLastName(lastName);
		for (CourseStudent courseStudent : courseStudents)
			courseStudent.setStudent(this);
		this.courseStudents = Stream.of(courseStudents).collect(Collectors.toSet());
	}
	
	public void addCourse(Course course) {
		CourseStudent courseStudent = new CourseStudent(this, course);
		courseStudents.add(courseStudent);
		course.getCourseStudents().add(courseStudent);		
	}
	
}
