package com.stacktrace.service;

import java.util.List;
import java.util.Set;

import com.stacktrace.entity.Title;


/**
 * @author jorge
 *
 */
public interface ITitleService {

	public List<Title> findAll();
	
	public Title findById(long id);
	
	public Title save(Title title);

	void delete(Title title);

	List<Title> findByTeacherId(long id);

	List<Title> saveAll(Set<Title> titles);
}
