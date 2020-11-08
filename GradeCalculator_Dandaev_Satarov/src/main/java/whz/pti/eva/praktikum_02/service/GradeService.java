package whz.pti.eva.praktikum_02.service;
import java.util.List;
import whz.pti.eva.praktikum_02.domain.Grade;

public interface GradeService {
	
	List<Grade> listAllGrades();
	boolean addGrade(String lecture, String grade);
	double calculcateAverage();
}
