package whz.pti.eva.praktikum_02.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import whz.pti.eva.praktikum_02.domain.Grade;
import whz.pti.eva.praktikum_02.domain.GradeRepository;

/**
 * @author user
 *
 */
@Service
public class GradeServiceImpl implements GradeService {

	@Autowired
	private GradeRepository gradeRepository;
	private Double noteSum;
	private Double noteSum1;
	
	
	/**
	 * traegt eine neue Note in die Liste ein
	 * @param lecture String - Name von Unterricht  
	 * @param grade String - Note von Unterricht
	 */
	@Override
	public boolean addGrade(String lecture, String grade) {
		try {
			noteSum1 = Double.parseDouble(grade);
			gradeRepository.save(new Grade(lecture, grade));
			return true;
		}
		catch(NumberFormatException e) {
			System.out.print("Number format error");
			return false;
		}
	}
	
	/**
	 * gibt eine Liste aller eingetragenen Noten zurueck
	 * @return List mit der Graden
	 */
	@Override
	public List<Grade> listAllGrades() {
		return gradeRepository.findAll();
	}
	
	/**
	 * gibt den berechneten Notendurchschnitt zurueck
	 * @return double-Typ-Zahl 
	 */
	@Override
	public double calculcateAverage() {
		List<Grade> grades = gradeRepository.findAll();
		if (grades.isEmpty()) {
			return 0;
		}
		noteSum = Double.MIN_VALUE;
		grades.stream().forEach((g) -> noteSum += Double.parseDouble(g.getGrade()));
		return noteSum/grades.size();
	}
}

