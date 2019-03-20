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
	TeacherRepository teacherRepository;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	CourseRepository courseRepository;

	@Bean
	public CommandLineRunner testRepositoriesMethods() {
		return (args) -> {

			// Teacher Repository Test
			Teacher newTeacher = new Teacher("Rosenda");
			teacherRepository.save(newTeacher);

			teacherRepository.save(new Teacher("Luis"));
			Teacher teacher3 = teacherRepository.save(new Teacher("Sandoval"));
			//for (Teacher teacher : teacherRepository.findAll()) {
				//log.info(teacher.toString());
			//}

			// Course Repository Test
			Course newCourse = new Course();
			newCourse.setName("Gestion empresarial");
			courseRepository.save(newCourse);
			courseRepository.save(new Course("Programacion 1"));
			courseRepository.save(new Course("Programacion 2"));

			log.info(courseRepository.findById(1L).toString());

			// Student Repository Test
			Student newStudent = new Student();
			newStudent.setName("Roland");

			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yy");
			newStudent.setBirthday(sdf.parse("9-02-17"));
			studentRepository.save(newStudent);
			Student student2 = studentRepository.save(new Student("Manuel","Peralta"));
			studentRepository.save(new Student("Juana","Molina"));
						
			// Add student in a course
			newCourse.addStudent(newStudent);
			newCourse.addStudent(student2);

			// Add teacher in a course
			newCourse.addTeacher(newTeacher);
			newCourse.addTeacher(teacher3);
			
			courseRepository.save(newCourse);

			for (Student student : studentRepository.findAll()) {
				log.info(student.toString());

				for (Course course : student.getCourses()) {
					log.info(course.toString());
				}
			}

			for (Course course : courseRepository.findAll()) {
				log.info(course.toString());

				for (Student student : course.getStudents()) {
					log.info(student.toString());
				}
			}

		};
	}

}
