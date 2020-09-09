package technocite.tn.telecite.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import technocite.tn.telecite.entities.Projet;
import technocite.tn.telecite.entities.Sprint;

public interface IProjet  extends JpaRepository<Projet, Long>{

	Projet findByNomProjet(String nomProjet);

	Projet findBySprints(Optional<Sprint> sprint);


}
