package com.stacktrace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacktrace.entity.Student;
import com.stacktrace.entity.Teacher;
import com.stacktrace.repository.StudentRepository;
import com.stacktrace.repository.TeacherRepository;


@RestController
@SpringBootApplication
public class CursoGestionApplication {

	private static final Logger log = LoggerFactory.getLogger(CursoGestionApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(CursoGestionApplication.class, args);
	}
	
	@RequestMapping("/")
	public String Saludo() {
		return "Hello index";
	}
	
	@Autowired
	StudentRepository studentRepository;

	@Bean
	public CommandLineRunner testRepositoriesMethods(TeacherRepository teacherRepository) {
		return (args) -> {
			Teacher teacherSave = new Teacher();
			teacherSave.setName("Rosenda");			
			teacherRepository.save(teacherSave);
			
			for (Teacher teacher : teacherRepository.findAll()) {
				log.info(teacher.toString());
			}
			
			Student studentSave = new Student();
			studentSave.setName("Roland");			
			studentRepository.save(studentSave);
			
			for (Student student : studentRepository.findAll()) {
				log.info(student.toString());
			}
		};
	}
	

}
