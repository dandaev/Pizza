package whz.pti.eva.praktikum_02.boundary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import whz.pti.eva.praktikum_02.domain.Grade;
import whz.pti.eva.praktikum_02.service.GradeService;

@Controller
public class GradeController {
	
	@Autowired
	private GradeService gradeService;
	
	@RequestMapping("/grades")
	public String listAllGrades(Model model) {
		List<Grade> grades = gradeService.listAllGrades();
		model.addAttribute("grades", grades);
		return "grades";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addGrade(@RequestParam String lecture,@RequestParam String grade) {
		gradeService.addGrade(lecture, grade);
		return "redirect:grades";
	}
}
