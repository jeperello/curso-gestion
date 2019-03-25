package com.stacktrace.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Title {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //@ManyToOne
    //@JoinColumn
    //private Teacher teacher;

    @ManyToMany(mappedBy = "titles", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<Teacher> teachers = new HashSet<Teacher>();
    
    //@Column(unique = true)
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
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @param teacher add teachers set
	 */
	public void addTeacher(Teacher teacher) {
		this.teachers.add(teacher);
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
