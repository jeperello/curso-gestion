package com.stacktrace.service;

import java.util.List;

import com.stacktrace.entity.Title;
import com.stacktrace.entity.Training;


/**
 * @author jorge
 *
 */
public interface ITrainingService {

	public List<Training> findAll();
	
	public Training findById(long id);
	
	public Training save(Training training);

	void delete(Training training);

	List<Training> findByTeacherId(long id);
}
