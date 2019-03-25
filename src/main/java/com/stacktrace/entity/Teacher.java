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

	private String training;
	// private String title;

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
	@OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
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
        this.titles.forEach(title -> title.setTeacher(this));
	}
	
	public void addTitle(Title title) {
		this.titles.add(title);
		title.setTeacher(this);
	}

	public Set<Title> getTitles() {
		return titles;
	}

}
