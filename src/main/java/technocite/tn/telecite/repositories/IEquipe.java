package technocite.tn.telecite.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import technocite.tn.telecite.entities.Employe;
import technocite.tn.telecite.entities.Equipe;

public interface IEquipe  extends JpaRepository<Equipe, Long>{
	Equipe findByNomEquipe (String nomEquipe);

	Equipe findByEmployes(Optional<Employe> employe);

}
