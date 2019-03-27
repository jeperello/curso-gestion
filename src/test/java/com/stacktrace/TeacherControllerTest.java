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
import com.stacktrace.entity.Teacher;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TeacherControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private JacksonTester<Teacher> teacherJson;

	@Before
	public void setUp() throws Exception {
		JacksonTester.initFields(this, new ObjectMapper());
	}

	@Test
	public void SaveTeacherOK() throws Exception {
		Teacher teacher = new Teacher("Jhan", "Paul", "M", "dni", 35669877L);
		// when
		MockHttpServletResponse response = this.mockMvc.perform(
				post("/api/teachers").contentType("application/json").content(teacherJson.write(teacher).getJson()))
				.andReturn().getResponse();
		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
	}

	@Test
	public void UpdateTeacherOK() throws Exception {
		Teacher teacher = new Teacher("Jhan", "Paul", "M", "dni", 35669877L);
		teacher.setId(1L);
		// when
		MockHttpServletResponse response = this.mockMvc.perform(
				put("/api/teachers").contentType("application/json").content(teacherJson.write(teacher).getJson()))
				.andReturn().getResponse();
		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
	}

	@Test
	public void UpdateTeacherNotFound() throws Exception {
		Teacher teacher = new Teacher("Jhan", "Paul", "M", "dni", 35669877L);
		teacher.setId(88L);
		// when
		MockHttpServletResponse response = this.mockMvc.perform(
				put("/api/teachers").contentType("application/json").content(teacherJson.write(teacher).getJson()))
				.andReturn().getResponse();
		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
	}

	@Test
	public void DeleteTeacher() throws Exception {	
		// when
		MockHttpServletResponse response = this.mockMvc.perform(
				delete("/api/teachers/2").contentType("application/json"))
				.andReturn().getResponse();
		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}

}
