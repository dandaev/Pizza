package whz.pti.eva.praktikum_02.domain;

import javax.persistence.Entity;

@Entity
public class Grade {
	private String lecture;
	private String grade;
	
	public Grade(String lecture, String grade) {
		super();
		this.lecture = lecture;
		this.grade = grade;
	}

	public String getLecture() {
		return lecture;
	}

	public void setLecture(String lecture) {
		this.lecture = lecture;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
}
