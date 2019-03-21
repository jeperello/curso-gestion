package com.stacktrace.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Teacher")
public class Teacher extends Person implements Serializable {

	private String training;
	private String title;

	/**
	 * Courses
	 */
	@ManyToMany(mappedBy = "teachers")
	private Set<Course> courses;

	/**
	 * Constructors
	 */
	public Teacher() {
	}

	public Teacher(String name) {
		super.setName(name);
	}

	public Teacher(String name, String lastName) {
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

	/**
	 * @return the training
	 */
	public String getTraining() {
		return training;
	}

	/**
	 * @param training the training to set
	 */
	public void setTraining(String training) {
		this.training = training;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

}
