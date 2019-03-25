package com.stacktrace.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stacktrace.entity.Title;
import com.stacktrace.entity.Training;
import com.stacktrace.repository.TitleRepository;

@Service
public class TitleService implements ITitleService {

	@Autowired
	TitleRepository repository;
	
	@Override
	public List<Title> findAll() {
		List <Title> titles = (List<Title>) repository.findAll();
		return titles;
	}

	@Override
	public Title findById(long id) {
		Title title = repository.findById(id).orElse(null);
		return title;
	}

	@Override
	public Title save(Title title) {
		return repository.save(title);
	}
	
	@Override
	public void delete(Title title) {
		repository.delete(title);
	}

	@Override
	public List<Title> findByTeacherId(long id) {
		//List <Title> titles = (List<Title>) repository.findByTeacherId(id);
		//return titles;
		return (List<Title>) new HashSet<Title>();
	}
}
