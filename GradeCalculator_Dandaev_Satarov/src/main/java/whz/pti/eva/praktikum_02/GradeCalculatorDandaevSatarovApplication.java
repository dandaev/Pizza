package whz.pti.eva.praktikum_02;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
/**
 * GradeCalculator ist ein Spring Boot Lehrprojekt des Fachs Entwicklung verteilter
 * Anwendungen 2020.
 * @author Alybek DANDAEV, Bekzhan SATAROV (Gruppe 09)
 * @date 13. November 2020
 */
@SpringBootApplication
public class GradeCalculatorDandaevSatarovApplication {

	public static void main(String[] args) {
		SpringApplication.run(GradeCalculatorDandaevSatarovApplication.class, args);
	}
	@Bean
	CommandLineRunner init() {
		return (evt) -> System.out.println("Hello Welt!");
	}
}
