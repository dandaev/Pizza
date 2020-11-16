package whz.pti.eva.praktikum_02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import whz.pti.eva.praktikum_02.domain.GradeRepository;
import whz.pti.eva.praktikum_02.service.GradeServiceImpl;

@SpringBootTest
class GradeCalculatorDandaevSatarovApplicationTests {
	
	@Autowired
	private GradeServiceImpl gradeServiceImpl;
	@Autowired
	private GradeRepository gradeRepository;
	
	@BeforeEach
	public void init() {
		gradeRepository.deleteAll();
	}

	   /**
	   * Test fuer addGrade() und listAllGrades()
	   */
	  @Test
	  void testAddGradeAndListAllGradesMethod() {
	    gradeServiceImpl.addGrade("Mathe", "1.5");
	    gradeServiceImpl.addGrade("Eva", "1.2");
	    
	    assertEquals(2, gradeServiceImpl.listAllGrades().size());
	  }
	  
	  /**
	   * Test fuer calculateAverage()
	   */
	  @Test
	  void testCalculateAverageMethod() {
	    gradeServiceImpl.addGrade("Mathe", "2.0");
	    gradeServiceImpl.addGrade("Eva", "2.0");
	    
	    assertEquals(2.0, gradeServiceImpl.calculcateAverage());
	  }

	  /**
	   * Test fuer calculateAverage() wenn es keine Grades gibt
	   */
	  @Test
	  void testCalculateAverageMethodWithoutGrades() {
	    assertEquals(0, gradeServiceImpl.calculcateAverage());
	  }

}
