package com.stacktrace.entity;
import lombok.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class TeacherTitle implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn
    private Teacher teacher;
    
    @Id
    @ManyToOne
    @JoinColumn
    private Title title;
    
    public TeacherTitle() {
    }

    public TeacherTitle(Title title) {
        this.title = title;
    }

    public TeacherTitle(Teacher teacher, Title title) {
    	this.teacher = teacher;
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeacherTitle)) return false;
        TeacherTitle that = (TeacherTitle) o;
        return Objects.equals(teacher.getName(), that.teacher.getName()) &&
                Objects.equals(title.getName(), that.title.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacher.getName(), title.getName());
    }

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	/**
	 * @return the title
	 */
	public Title getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(Title title) {
		this.title = title;
	}


}