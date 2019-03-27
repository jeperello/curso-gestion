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
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "teacher_course", joinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
	private Set<Course> courses;

	/**
	 * Titles
	 */
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "teacher_title", joinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "title_id", referencedColumnName = "id"))
	private Set<Title> titles;

	/**
	 * Constructors
	 */
	public Teacher() {
	}

	public Teacher(String name, String lastName) {
		super.setName(name);
		super.setLastName(lastName);
	}

	public Teacher(String name, String lastName, Set<Course> courses) {
		super.setName(name);
		super.setLastName(lastName);
		this.courses = courses;
	}

	public Teacher(String name, String lastName, String sex, String documentType, Long documentNumber) {
		super.setName(name);
		super.setLastName(lastName);
		super.setDocumentType(documentType);
		super.setDocumentNumber(documentNumber);
		super.setSex(sex);
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

	/**
	 * @return the titles
	 */
	public Set<Title> getTitles() {
		return titles;
	}

	/**
	 * @param titles the titles to set
	 */
	public void setTitles(Set<Title> titles) {
		this.titles = titles;
	}

	/**
	 * addTitle
	 * 
	 * @param title
	 */
	public void addTitle(Title title) {
		if (this.titles != null) {
			this.titles.add(title);
		} else {
			this.titles = new HashSet<Title>() {
				{
					add(title);
				}
			};
		}
	}

}
