package com.stacktrace.entity;

import javax.persistence.*;

@Entity
public class Title {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Teacher teacher;

    private String name;

    public Title() {
	}

	public Title(String name) {
        this.name = name;
    }

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	public String getName() {
		return name;
	}
}
