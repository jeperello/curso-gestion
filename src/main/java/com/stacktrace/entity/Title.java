package com.stacktrace.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Title {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
		
	@JsonIgnore
	@ManyToMany(mappedBy = "titles")
	private Set<Teacher> teachers = new HashSet<>();
	
    @Column(unique = true)
    private String name;

    public Title() {
	}

	public Title(String name) {
        this.name = name;
    }
	
	public String getName() {
		return name;
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
	 * @param name the name to set
	 */
	public void setName(String name) {
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

}
