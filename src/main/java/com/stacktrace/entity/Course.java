package com.stacktrace.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Course {

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private int duration;
	private String description;
	private Double approve;

	@ManyToMany
	private Set<Student> students = new HashSet<>();

	@ManyToMany
	private Set<Teacher> teachers = new HashSet<>();
	
	public Course() {}

	public Course(String name) {
		this.name = name;
	}
	
	/**
	 * @return the teachers
	 */
	public Set<Teacher> getTeachers() {
		return teachers;
	}

	/**
	 * @param teachers the teachers to set
	 */
	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the students
	 */
	public Set<Student> getStudents() {
		return students;
	}

	public void addStudent(Student student) {
		students.add(student);
	}
	
	public void addTeacher(Teacher teacher) {
		teachers.add(teacher);
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the approve
	 */
	public Double getApprove() {
		return approve;
	}

	/**
	 * @param approve the approve to set
	 */
	public void setApprove(Double approve) {
		this.approve = approve;
	}

	@Override
	public String toString() {
		return String.format("Course[id='%d', name='%s']", id, name);
	}
}
