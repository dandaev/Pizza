package whz.pti.eva.praktikum_02;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import whz.pti.eva.praktikum_02.domain.GradeRepository;
import whz.pti.eva.praktikum_02.service.GradeService;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class GradeControllerTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	private GradeService gradeSrvice;
	
	@Autowired
	private GradeRepository gradeRepository;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(wac)
				.build();
		gradeRepository.deleteAll();
		gradeSrvice.addGrade("Math", "1.6");
		gradeSrvice.addGrade("Eva", "1.0");
	}
	
	@Test
	public void getListAllGrades() throws Exception {
		mockMvc.perform(get("/grades")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				)
				.andExpect(status().isOk())
				.andExpect(view().name("grades"))
				.andExpect(model().attribute("grades", hasSize(2)))
				.andExpect(model().attribute("notendurchschnitt", 1.3))
				.andDo(print());
	}
	
	@Test
	public void postAddGrade() throws Exception {
		mockMvc.perform(post("/add")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.param("lecture", "Geschichte")
				.param("grade", "1.6")
				)
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("grades"));
	}
}
