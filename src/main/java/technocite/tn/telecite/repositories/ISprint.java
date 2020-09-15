package technocite.tn.telecite.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import technocite.tn.telecite.entities.Equipe;
import technocite.tn.telecite.entities.Projet;
import technocite.tn.telecite.entities.Sprint;

public interface ISprint  extends JpaRepository<Sprint, Long>{
	
	 List<Sprint> findByEtatSprint(String etatSprint);
	List<Sprint> findByProjet(Optional<Projet> projet);
	 List<Sprint> findByOrderByProjetAsc();
	Equipe findByProjet_Equipe(Long idSprint);

	}

