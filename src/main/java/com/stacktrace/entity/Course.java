package com.stacktrace.entity;

public class Course {
	
	private String name;
	private int duration;
	private String description;
	private Double approve;
	
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

}
