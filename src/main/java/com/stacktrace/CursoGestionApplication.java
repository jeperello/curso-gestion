package com.stacktrace;

import java.text.SimpleDateFormat;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
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

@SpringBootApplication
public class CursoGestionApplication extends SpringBootServletInitializer {

	private static final Logger log = LoggerFactory.getLogger(CursoGestionApplication.class);
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CursoGestionApplication.class);
	}

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
					add(new Course("Curso Gestion", "Curso sobre Gestion empresarial", 36, 7D));
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
						
			// Save teacher-training
			Teacher teacher3 = teacherService.save(new Teacher("Juan", "Sandoval"));
			teacher3.addTraining(new Training("Gestion empresarial"));
			teacherService.save(teacher3);
	
			// Add teacher-course
			Course courseA = new Course("Wamp Course", "Wamp Course", 36, 7D);
			Course courseB = new Course("Xamp Course", "Xamp Course", 36, 7D);
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

			// Add student-course
			Student studentA = new Student("Carlos", "Sanchez","M" , "DNI", 36002114L);
			Course courseC = new Course("Ingles tecnico 1", "Ingles tecnico 1", 36, 8D);			
			StudentCourse studentCourse = new StudentCourse(studentA, courseC, 8D);
			studentA.getStudentCourses().add(studentCourse);
			courseService.save(courseC);
			studentService.save(studentA);			
		
			Student studentB = new Student("Alisa", "Vilchez", "F", "DNI", 54411454L);
			Course courseD = new Course("Ingles tecnico 2", "Ingles tecnico 2", 36, 8D);			
			StudentCourse studentCourse2 = new StudentCourse(studentB, courseD, 8D);
			studentB.getStudentCourses().add(studentCourse2);			
			courseService.save(courseD);
			studentService.save(studentB);				
			
			Student student3 = new Student("Carlos", "Villagran","M" , "DNI", 36002114L);
			Course course3 = new Course("Ingles tecnico 3", "Ingles tecnico 3", 36, 9D);			
			StudentCourse studentCourse3 = new StudentCourse(student3, course3, 8D);
			student3.getStudentCourses().add(studentCourse3);
			StudentCourse studentCourse4 = new StudentCourse(student3, courseD, 8D);
			student3.getStudentCourses().add(studentCourse4);
			courseService.save(course3);
			studentService.save(student3);	
						
			Student studentNew = new Student("Luciana", "Villegas","F" , "DNI", 36002114L);
			Course course8 = courseService.findById(8L);			
			StudentCourse studentCourse8 = new StudentCourse(studentNew, course8, 7D);
			studentNew.getStudentCourses().add(studentCourse8);
			studentService.save(studentNew);	
			
		};
	}

}
