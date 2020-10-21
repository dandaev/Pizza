package whz.pti.eva.praktikum_02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import whz.pti.eva.praktikum_02.service.GradeServiceImpl;

@SpringBootTest
class GradeCalculatorDandaevSatarovApplicationTests {
	
	@Autowired
	private GradeServiceImpl gradeServiceImpl;

	@Test
	void testAddGradeAndListAllGradesMethod() {
		gradeServiceImpl.addGrade("Mathe", "1.5");
		gradeServiceImpl.addGrade("Eva", "1.2");
		
		assertEquals(2, gradeServiceImpl.listAllGrades().size());
	}
	
	@Test
	void testCalculateAverageMethod() {
		gradeServiceImpl.addGrade("Mathe", "1.5");
		gradeServiceImpl.addGrade("Eva", "1.2");
		
		assertEquals(1.35, gradeServiceImpl.calculcateAverage());
	}

}
