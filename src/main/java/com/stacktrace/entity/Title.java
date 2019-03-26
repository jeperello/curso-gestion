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
	@OneToMany(mappedBy = "title", cascade = CascadeType.ALL)
    private Set<TeacherTitle> teacherTitles = new HashSet<>();
    
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
	 * @return the teacherTitles
	 */
	public Set<TeacherTitle> getTeacherTitles() {
		return teacherTitles;
	}

	/**
	 * @param teacherTitles the teacherTitles to set
	 */
	public void setTeacherTitles(Set<TeacherTitle> teacherTitles) {
		this.teacherTitles = teacherTitles;
	}

}
