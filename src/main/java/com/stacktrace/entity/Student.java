package com.stacktrace.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Student")
public class Student extends Person implements Serializable {

	@ManyToMany(mappedBy = "students")
	Set<Course> courses;

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
	
}
