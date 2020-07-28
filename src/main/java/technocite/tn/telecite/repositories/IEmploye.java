package technocite.tn.telecite.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import technocite.tn.telecite.entities.Bureau;
import technocite.tn.telecite.entities.Employe;
import technocite.tn.telecite.entities.Equipe;

public interface IEmploye extends JpaRepository<Employe, Long>{
	Employe findByEmailAndPassword(String email,String password);
	List<Employe> findByActive(Boolean active);
	 List<Employe> findByEquipe(Equipe equipe);

	Employe findByNomEmploye (String nomEmploye);
	Employe findByNomEmployeAndPrenomEmploye (String nomEmploye ,String PrenomEmploye);
	List<Employe> findByBureau(Bureau bureau);
	}

