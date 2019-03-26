package com.stacktrace.controller;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacktrace.entity.Teacher;
import com.stacktrace.entity.TeacherTitle;
import com.stacktrace.entity.Title;
import com.stacktrace.entity.Training;
import com.stacktrace.service.TeacherService;
import com.stacktrace.service.TitleService;

@RestController
@RequestMapping(TitleController.TITLE_RESOURCE)
public class TitleController {

	public static final String TITLE_RESOURCE = "/api/titles";

	@Autowired
	TitleService titleService;

	@Autowired
	TeacherService teacherService;

	/**
	 * Retrieve all titles.
	 * 
	 * @return a List of titles
	 */
	@GetMapping
	public List<Title> getAllTitles() {
		List<Title> titles = titleService.findAll();
		return titles;
	}

	/**
	 * Retrieve a teacher by id.
	 * 
	 * @param id
	 * @return a teacher
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getTitleById(@PathVariable("id") Long id) {
		Title title = titleService.findById(id);
		if (title != null) {
			return new ResponseEntity<>(title, HttpStatus.OK);
		}
		return new ResponseEntity<>(Collections.singletonMap("id", id), HttpStatus.NOT_FOUND);
	}

	/**
	 * Save a title.
	 * 
	 * @param title
	 * @return return saved title id
	 */
	@PostMapping
	public ResponseEntity<Map<String, Object>> saveTitle(@RequestBody @Valid Title title) {
		try {
			Title titleSaved = titleService.save(title);
			return new ResponseEntity<>(Collections.singletonMap("id", titleSaved.getId()), HttpStatus.CREATED);
		} catch (Exception ex) {
			return new ResponseEntity<>(Collections.singletonMap("error", ex.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Delete a title
	 * 
	 * @param titleId
	 * @return return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTeacher(@PathVariable(value = "id") Long id) {
		Title title = titleService.findById(id);
		if (title != null) {
			titleService.delete(title);
			return ResponseEntity.ok().build();
		}
		return new ResponseEntity<>(Collections.singletonMap("id", id), HttpStatus.NOT_FOUND);
	}

	/**
	 * Save teacher-title relationship.
	 * 
	 * @param title
	 * @return return saved title id
	 */
	@PostMapping("/{id}")
	public ResponseEntity<Map<String, Object>> saveTeacherTitle(@RequestBody Long teacherId,
			@PathVariable(value = "id") Long id) {
		try {
			Title title = titleService.findById(id);
			if (title != null) {
				Teacher teacher = teacherService.findById(teacherId);
				if (teacher != null) {
					TeacherTitle teacher_title = new TeacherTitle(teacher, title);
					teacher.setTeacherTitles(new HashSet<TeacherTitle>() {
						{
							add(teacher_title);
						}
					});
					teacherService.save(teacher);	
					return new ResponseEntity<>(Collections.singletonMap("teacher-title-id:",id ), 
							HttpStatus.CREATED);
					
				}
				return new ResponseEntity<>(Collections.singletonMap("teacher-id", id), HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(Collections.singletonMap("title-id", id), HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			return new ResponseEntity<>(Collections.singletonMap("error", ex.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
