package com.stacktrace;

import java.text.SimpleDateFormat;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import com.stacktrace.entity.Course;
import com.stacktrace.entity.StudentCourse;
import com.stacktrace.entity.Student;
import com.stacktrace.entity.Teacher;
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
			teacherService.saveAll(new HashSet<Teacher>() {
				{
					add(new Teacher("Jorge", "Perello", "M", "DNI", 31445874L));
					add(new Teacher("Julian", "Martinez", "M", "DNI", 27445874L));
					add(new Teacher("Monica", "Labertuchi", "F", "DNI", 34557448L));
					add(new Teacher("Jhonatan","Calderon","M" , "DNI", 29885699L));
					add(new Teacher("Jesus", "Hurtado", "M", "DNI", 30332554L));
				}
			});

			/*
			 * Course Test service
			 */
			courseService.saveAll(new HashSet<Course>() {
				{
					add(new Course("Java 110", "Java 110", 28, 8D));
					add(new Course("Java 275", "Java 275", 36, 8D));
					add(new Course("Computación 1", "Computación 1", 7D));
					add(new Course("Gestion empresarial", "Gestion empresarial", 7D));
					add(new Course("Curso Gestion"));
				}
			});
			
			/*
			 * Student Test service
			 */
			Student newStudent = new Student("Roland", "Numberone");
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yy");
			newStudent.setBirthday(sdf.parse("9-02-17"));
			studentService.save(newStudent);
			
			studentService.saveAll(new HashSet<Student>() {
				{
					add(new Student("Raquel","Dolccemascolo","F" , "DNI", 29885698L));
					add(new Student("Tamara","Infante","F" , "DNI", 29554778L));
					add(new Student("Matias","Schaefer","M" , "DNI", 36002114L));
					add(new Student("Genesis", "Montilla", "F", "DNI", 35665224L ));
				}
			});

			/*
			 * Teacher's titles service
			 */			
			titleService.saveAll(new HashSet<Title>() {
				{
					add(new Title("Ingeniero Electronico"));
					add(new Title("Ingeniería en Computación"));
					add(new Title("Licenciatura en Ciencias Matemáticas"));
					add(new Title("Licenciatura en Física"));
					add(new Title("Profesorado en Matemáticas"));
				}
			});
			
			/*
			 * -----------------------------------------
			 * ----------Relations Test Data------------
			 * -----------------------------------------
			 */
			// Save teacher-titles
			Teacher teacher4 = teacherService.findById(4L);
			Title title1 = titleService.findById(1L);
			//teacher4.addTitle(title1);	
			teacher4.setTitles(new HashSet<Title>() {
				{
					add(title1);
				}
			});
			teacherService.save(teacher4);
						
			// Save teacher trainings
			Teacher teacher3 = teacherService.save(new Teacher("Juan", "Sandoval"));
			teacher3.addTraining(new Training("Gestion empresarial"));
			teacherService.save(teacher3);
	
			// Add teacher in a course
			Course courseA = new Course("Course A");
			Course courseB = new Course("Course B");

			teacherService.save(new Teacher("Roberto", "Gomez", new HashSet<Course>() {
				{
					add(courseA);
					add(courseB);
				}
			}));

			Teacher teacher2 = teacherService.findById(2L);
			Course course1 = courseService.findById(1L);
			teacher2.setCourses(new HashSet<Course>() {
				{
					add(course1);
				}
			});
			teacherService.save(teacher2);

			// Add student in a course
			Student studentA = new Student("Carlos", "Sanchez");
			Course courseC = new Course("Course C");

			StudentCourse studentCourse = new StudentCourse(studentA, courseC, 8D);
			studentA.getStudentCourses().add(studentCourse);

			courseService.save(courseC);
			studentService.save(studentA);
			
		
		};
	}

}
