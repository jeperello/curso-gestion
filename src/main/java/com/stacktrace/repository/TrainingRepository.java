package com.stacktrace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stacktrace.entity.Training;


public interface TrainingRepository extends JpaRepository<Training, Long>{
	

}
