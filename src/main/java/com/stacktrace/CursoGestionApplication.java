package com.stacktrace;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacktrace.entity.Course;
import com.stacktrace.entity.CourseStudent;
import com.stacktrace.entity.Student;
import com.stacktrace.entity.Teacher;
import com.stacktrace.entity.TeacherTitle;
import com.stacktrace.entity.Title;
import com.stacktrace.entity.Training;
import com.stacktrace.service.CourseService;
import com.stacktrace.service.StudentService;
import com.stacktrace.service.TeacherService;
import com.stacktrace.service.TitleService;

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

	@Autowired
	TitleService titleService;

	@Bean
	public CommandLineRunner testRepositoriesMethods() {
		return (args) -> {
			/*
			 * Teacher Test service
			 */
			/*
			 * Teacher newTeacher = new Teacher("Rosendo", "Rosales");
			 * teacherService.save(newTeacher); teacherService.save(new Teacher("Luis",
			 * "Mercado")); Teacher teacher3 = teacherService.save(new Teacher("Juan",
			 * "Sandoval")); teacherService.save(new Teacher("Jack", "Bauer"));
			 * teacherService.save(new Teacher("Chloe", "O'Brian"));
			 */

			/*
			 * Course Test service
			 */
			Course newCourse = new Course();
			newCourse.setName("Gestion empresarial");
			courseService.save(newCourse);
			// courseService.save(new Course("Java 110"));
			// courseService.save(new Course("Java 275"));

			/*
			 * Student Test service
			 */
			Student newStudent = new Student("Roland", "Numberone");
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yy");
			newStudent.setBirthday(sdf.parse("9-02-17"));
			studentService.save(newStudent);

			Student student2 = studentService.save(new Student("Manuel", "Peralta"));
			// studentService.save(new Student("Juana","Molina"));
			// studentService.save(new Student("Kim", "Bauer"));
			// studentService.save(new Student("David", "Palmer"));
			// studentService.save(new Student("Michelle", "Dessler"));

			/*
			 * Relations Repository Test
			 */
			// Add student in a course
			// newCourse.addStudent(newStudent);
			// newCourse.addStudent(student2);

			// Add teacher in a course
			// newCourse.addTeacher(newTeacher);
			// newCourse.addTeacher(teacher3);
			// courseService.save(newCourse);

			// Save teacher with titles
			Title titleSaved = titleService.save(new Title("Curso Gestion"));

			Teacher teacher3 = teacherService.save(new Teacher("Juan", "Sandoval"));

			teacherService.save(new Teacher("Name", "last name", new TeacherTitle(titleSaved)));

			teacher3.addTraining(new Training("Gestion empresarial"));
			teacherService.save(teacher3);

			Student studentTest = new Student("Abelardo", "Figueras", new CourseStudent(newCourse));
			Set<CourseStudent> test = studentTest.getCourseStudents();
			studentService.save(studentTest);

			Title bachiller = titleService.save(new Title("Bachiller"));
			Teacher newTeacher = new Teacher("Rosendo", "Rosales");
			TeacherTitle teacher_title = new TeacherTitle(newTeacher, bachiller);
			newTeacher.setTeacherTitles(new HashSet<TeacherTitle>() {
				{
					add(teacher_title);
				}
			});

			teacherService.save(newTeacher);

			/*
			 * Title title = titleService.findById(2L); if (title != null) { Teacher teacher
			 * = teacherService.findById(2L); if (teacher != null) { TeacherTitle
			 * teacher_title1 = new TeacherTitle(teacher, title);
			 * teacher.getTeacherTitles().add(teacher_title1);
			 * 
			 * teacher.setTeacherTitles(new HashSet<TeacherTitle>() { { add(teacher_title1);
			 * } });
			 * 
			 * teacherService.save(teacher); } }
			 */
		};
	}

}
