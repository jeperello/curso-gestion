package com.stacktrace.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "Teacher")
public class Teacher extends Person {

	/**
	 * Training
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
	private Set<Training> training = new HashSet<Training>();

	/**
	 * Courses
	 */
	@JsonIgnore
	@ManyToMany(mappedBy = "teachers", fetch = FetchType.EAGER)
	private Set<Course> courses;

	/**
	 * Titles
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Set<TeacherTitle> teacherTitles = new HashSet<TeacherTitle>();

	/**
	 * @return the teacherTitles
	 */
	public Set<TeacherTitle> getTeacherTitles() {
		return teacherTitles;
	}

	/**
	 * @param teacherTitles the teacherTitles to set
	 */
	public void setTeacherTitles(Set<TeacherTitle> teacherTitles) {
		this.teacherTitles = teacherTitles;
	}

	/**
	 * Constructors
	 */
	public Teacher() {
	}

	public Teacher(String name, String lastName) {
		super.setName(name);
		super.setLastName(lastName);
	}

	public Teacher(String name, String lastName, TeacherTitle... teacherTitles) {
		super.setName(name);
		super.setLastName(lastName);
		for (TeacherTitle teacherTitle : teacherTitles)
			teacherTitle.setTeacher(this);
		this.teacherTitles = Stream.of(teacherTitles).collect(Collectors.toSet());
	}

	public void addTraining(Training training) {
		this.training.add(training);
		training.setTeacher(this);
	}

	/**
	 * @return the training
	 */
	public Set<Training> getTraining() {
		return training;
	}

	/**
	 * @param training the training to set
	 */
	public void setTraining(Set<Training> training) {
		this.training = training;
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
