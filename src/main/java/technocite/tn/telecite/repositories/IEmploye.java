package technocite.tn.telecite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import technocite.tn.telecite.entities.Employe;

public interface IEmploye extends JpaRepository<Employe, Long>{
	Employe findByEmailAndPassword(String email,String password);
}
