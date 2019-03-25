package com.stacktrace.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import lombok.Data;

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
	@ManyToMany
	@JoinTable(
			  name = "teacher_title", 
			  joinColumns = @JoinColumn(name = "teacher_id"), 
			  inverseJoinColumns = @JoinColumn(name = "title_id"))
	private Set<Title> titles = new HashSet<Title>();

	/**
	 * Constructors
	 */
	public Teacher() {
	}

	public Teacher(String name, String lastName) {
		super.setName(name);
		super.setLastName(lastName);
	}

	public Teacher(String name, String lastName, Title... titles) {
		super.setName(name);
		super.setLastName(lastName);
		this.titles = Stream.of(titles).collect(Collectors.toSet());
		this.titles.forEach(title -> title.addTeacher(this));
	}

	public void addTitle(Title title) {
		this.titles.add(title);
		//title.addTeacher(this);
	}

	public void addTraining(Training training) {
		this.training.add(training);
		training.setTeacher(this);
	}

	public Set<Title> getTitles() {
		return titles;
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
	 * @param titles the titles to set
	 */
	public void setTitles(Set<Title> titles) {
		this.titles = titles;
	}

}
