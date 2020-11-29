package technocite.tn.telecite;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;



@SpringBootApplication
@EnableScheduling

public class TeleciteApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(TeleciteApplication.class, args);

	}

	// @Override
	// public void run(String... args) throws Exception {
	// TODO Auto-generated method stub

	// Reunion reunion=new Reunion("r1");
	// Employe emp1=new Employe("e1");
	// Employe emp2=new Employe("e2");
	// Employe emp3=new Employe("e3");

	// reunion.getEmployes().add(emp1);
	// reunion.getEmployes().add(emp2);
	// reunion.getEmployes().add(emp3);

	/// emp1.getReunions().add(reunion);
	// emp2.getReunions().add(reunion);
	// emp3 .getReunions().add(reunion);
//this.reunionRepository.save(reunion);
	// }

}
