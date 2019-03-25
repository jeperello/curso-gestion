package com.stacktrace.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "course_student")
public class CourseStudent implements Serializable {
	
	@Id
	@ManyToOne
	@JoinColumn
	private Student student;

	@Id
	@ManyToOne
	@JoinColumn
	private Course course;

	public CourseStudent(Student student, Course course) {
		// this.courseStudentId = new CourseStudent.CourseStudentId();
		this.student = student;
		this.course = course;
	}

	public CourseStudent(Course course) {
		this.course = course;
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

	Double grade;

	/**
	 * Constructors
	 */
	public CourseStudent() {
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof CourseStudent))
			return false;
		CourseStudent that = (CourseStudent) o;
		return Objects.equals(student.getName(), that.student.getName())
				&& Objects.equals(course.getName(), that.course.getName()) && Objects.equals(grade, that.grade);
	}

	@Override
	public int hashCode() {
		return Objects.hash(student.getName(), course.getName(), grade);
	}
}
