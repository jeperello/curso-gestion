package com.stacktrace.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Student")
public class Student extends Person implements Serializable {

	@ManyToMany(mappedBy = "students", fetch=FetchType.EAGER)
	Set<Course> courses;
	
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
	
}
