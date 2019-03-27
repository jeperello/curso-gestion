package com.stacktrace;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stacktrace.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CourseControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private JacksonTester<Course> courseJson;

	@Before
	public void setUp() throws Exception {
		JacksonTester.initFields(this, new ObjectMapper());
	}

	@Test
	public void SaveCoursetOK() throws Exception {
		Course course = new Course("Ingles tecnico 2", "Ingles tecnico 2", 36, 8D);
		// when
		MockHttpServletResponse response = this.mockMvc.perform(
				post("/api/courses").contentType("application/json").content(courseJson.write(course).getJson()))
				.andReturn().getResponse();
		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
	}

	@Test
	public void UpdateCourseOK() throws Exception {
		Course course = new Course("Ingles tecnico 2", "Ingles tecnico 2", 36, 8D);
		course.setId(1L);
		// when
		MockHttpServletResponse response = this.mockMvc.perform(
				put("/api/courses").contentType("application/json").content(courseJson.write(course).getJson()))
				.andReturn().getResponse();
		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
	}

	@Test
	public void UpdateCourseNotFound() throws Exception {
		Course course = new Course("Ingles tecnico 2", "Ingles tecnico 2", 36, 8D);
		course.setId(88L);
		// when
		MockHttpServletResponse response = this.mockMvc.perform(
				put("/api/courses").contentType("application/json").content(courseJson.write(course).getJson()))
				.andReturn().getResponse();
		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
	}

	@Test
	public void DeleteCourse() throws Exception {	
		// when
		MockHttpServletResponse response = this.mockMvc.perform(
				delete("/api/courses/2").contentType("application/json"))
				.andReturn().getResponse();
		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}

}
