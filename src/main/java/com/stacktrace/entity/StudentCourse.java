package com.stacktrace.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "student_course")
public class StudentCourse implements Serializable {
	
	@Id
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;

	@Id
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
	Double grade;

	/**
	 * Constructors
	 */
	public StudentCourse(Student student, Course course) {
		// this.courseStudentId = new CourseStudent.CourseStudentId();
		this.student = student;
		this.course = course;
	}

	public StudentCourse(Course course) {
		this.course = course;
	}

	public StudentCourse() {
	}

	
	/**
	 * @return the student
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * @param student the student to set
	 */
	public void setStudent(Student student) {
		this.student = student;
	}

	/**
	 * @return the course
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * @param course the course to set
	 */
	public void setCourse(Course course) {
		this.course = course;
	}


	/**
	 * @return the grade
	 */
	public Double getGrade() {
		return grade;
	}

	/**
	 * @param grade the grade to set
	 */
	/**
	 * @param grade
	 */
	public void setGrade(Double grade) {
		this.grade = grade;
	}

	/*
	 * @Override public boolean equals(Object o) { if (this == o) return true; if
	 * (!(o instanceof StudentCourse)) return false; StudentCourse that =
	 * (StudentCourse) o; return Objects.equals(student.getName(),
	 * that.student.getName()) && Objects.equals(course.getName(),
	 * that.course.getName()) && Objects.equals(grade, that.grade); }
	 * 
	 * @Override public int hashCode() { return Objects.hash(student.getName(),
	 * course.getName(), grade); }
	 */
}
