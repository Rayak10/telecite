package technocite.tn.telecite.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import technocite.tn.telecite.entities.Projet;
import technocite.tn.telecite.entities.Sprint;

public interface ISprint  extends JpaRepository<Sprint, Long>{
	List<Sprint> findByEtat(String etat);
	 List<Sprint> findByProjet(Projet projet);
	}

