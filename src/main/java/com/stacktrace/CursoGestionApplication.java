package com.stacktrace;

import java.text.SimpleDateFormat;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacktrace.entity.Course;
import com.stacktrace.entity.Student;
import com.stacktrace.entity.Teacher;
import com.stacktrace.repository.CourseRepository;
import com.stacktrace.repository.StudentRepository;
import com.stacktrace.repository.TeacherRepository;
import com.stacktrace.service.CourseService;
import com.stacktrace.service.StudentService;
import com.stacktrace.service.TeacherService;


@RestController
@SpringBootApplication
public class CursoGestionApplication {

	private static final Logger log = LoggerFactory.getLogger(CursoGestionApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CursoGestionApplication.class, args);
	}

	@Autowired 
	TeacherService teacherService;

	@Autowired 
	StudentService studentService;
	
	@Autowired 
	CourseService courseService;

	@Bean
	public CommandLineRunner testRepositoriesMethods() {
		return (args) -> {
			/*
			 *  Teacher Test service
			 */			
			Teacher newTeacher = new Teacher("Rosendo", "Rosales");
			teacherService.save(newTeacher);
			teacherService.save(new Teacher("Luis", "Mercado"));
			Teacher teacher3 = teacherService.save(new Teacher("Juan", "Sandoval"));
			teacherService.save(new Teacher("Jack", "Bauer"));
			teacherService.save(new Teacher("Chloe", "O'Brian"));
			
			/*
			 *  Course Test service
			 */
			Course newCourse = new Course();
			newCourse.setName("Gestion empresarial");
			courseService.save(newCourse);
			courseService.save(new Course("Java 110"));
			courseService.save(new Course("Java 275"));

			/*
			 *  Student Test service
			 */
			Student newStudent = new Student("Roland", "Numberone");
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yy");
			newStudent.setBirthday(sdf.parse("9-02-17"));
			studentService.save(newStudent);
			
			Student student2 = studentService.save(new Student("Manuel","Peralta"));
			studentService.save(new Student("Juana","Molina"));
			studentService.save(new Student("Kim", "Bauer"));
			studentService.save(new Student("David", "Palmer"));
			studentService.save(new Student("Michelle", "Dessler"));
			
			/*
			 *  Relations Repository Test
			 */
			// Add student in a course
			newCourse.addStudent(newStudent);
			newCourse.addStudent(student2);
			
			// Add teacher in a course
			newCourse.addTeacher(newTeacher);
			newCourse.addTeacher(teacher3);			
			courseService.save(newCourse);		

			log.info("Testing services:");
			log.info("teacherService.findById(1).getName():");
			log.info(teacherService.findById(1L).getName());
			log.info("studentService.findById(1).getName():");
			log.info(studentService.findById(1).getName());
			log.info("courseService.findById(3).getName():");
			log.info(courseService.findById(3).getName());
		};
	}

}
