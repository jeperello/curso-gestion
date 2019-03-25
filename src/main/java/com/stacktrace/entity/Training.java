package com.stacktrace.entity;

import javax.persistence.*;

@Entity
public class Training {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Teacher teacher;

    private String name;

    public Training() {
	}

	public Training(String name) {
        this.name = name;
    }

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	public String getName() {
		return name;
	}
}
