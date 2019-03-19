package com.stacktrace.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Teacher")
public class Teacher extends Person implements Serializable {

	private String training;
	private String title;
	
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
