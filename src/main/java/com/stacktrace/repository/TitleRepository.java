package com.stacktrace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stacktrace.entity.Title;


public interface TitleRepository extends JpaRepository<Title, Long>{

	//List<Title> findByTeacherId(long id);

}
