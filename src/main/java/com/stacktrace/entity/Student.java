package com.stacktrace.entity;

import java.util.HashSet;
import java.util.Set;

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
	Set<StudentCourse> studentCourses;

	/**
	 * Constructors
	 */
	public Student() {
	}

	public Student(String name, String lastName) {
		super.setName(name);
		super.setLastName(lastName);
		studentCourses = new HashSet<>();
	}
	
	public Student(String name, String lastName, String sex, String documentType, Long documentNumber) {
		super.setName(name);
		super.setLastName(lastName);
		super.setDocumentType(documentType);
		super.setDocumentNumber(documentNumber);
		super.setSex(sex);
		studentCourses = new HashSet<>();
	}
	
	/**
	 * @return the studentCourses
	 */
	public Set<StudentCourse> getStudentCourses() {
		return studentCourses;
	}

	/**
	 * @param studentCourses the studentCourses to set
	 */
	public void setStudentCourses(Set<StudentCourse> studentCourses) {
		this.studentCourses = studentCourses;
	}

	
}
