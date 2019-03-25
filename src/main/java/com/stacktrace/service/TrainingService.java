package com.stacktrace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stacktrace.entity.Training;
import com.stacktrace.repository.TrainingRepository;


@Service
public class TrainingService implements ITrainingService {

	@Autowired
	TrainingRepository repository;
	
	@Override
	public List<Training> findAll() {
		List <Training> trainings = (List<Training>) repository.findAll();
		return trainings;
	}

	@Override
	public Training findById(long id) {
		Training training = repository.findById(id).orElse(null);
		return training;
	}

	@Override
	public Training save(Training training) {
		return repository.save(training);
	}
	
	@Override
	public void delete(Training training) {
		repository.delete(training);
	}

	@Override
	public List<Training> findByTeacherId(long id) {
		List <Training> trainings = (List<Training>) repository.findByTeacherId(id);
		return trainings;
	}
}
