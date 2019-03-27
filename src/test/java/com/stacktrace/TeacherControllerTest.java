package com.stacktrace;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
	public void SavePermissionOK() throws Exception {
		Teacher teacher = new Teacher("Jhan","Paul","M", "dni", 35669877L);
		// when
		MockHttpServletResponse response = this.mockMvc.perform(post("/api/teachers").contentType("application/json")
				.content(teacherJson.write(teacher).getJson())).andReturn().getResponse();
		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
	}


}
