package com.stacktrace.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Student")
public class Student extends Person{
	
	@JsonIgnore
	@ManyToMany(mappedBy = "students", fetch = FetchType.LAZY)
	Set<Course> courses = new HashSet<>();
	
	/**
	 * Constructors
	 */
	public Student(){}

	public Student(String name, String lastName){
		super.setName(name);
		super.setLastName(lastName);
	}

	/**
	 * @return the courses
	 */
	public Set<Course> getCourses() {
		return courses;
	}

	/**
	 * @param courses the courses to set
	 */
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	
	public void addCourse(Course course) {
		courses.add(course);
	}
	

}
