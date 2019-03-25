package com.stacktrace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stacktrace.entity.Training;


public interface TrainingRepository extends JpaRepository<Training, Long>{
	
	List<Training> findByTeacherId(long id);

}
