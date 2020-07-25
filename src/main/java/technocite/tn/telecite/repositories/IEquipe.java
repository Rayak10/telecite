package technocite.tn.telecite.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import technocite.tn.telecite.entities.Equipe;

public interface IEquipe  extends JpaRepository<Equipe, Long>{
	Equipe findByNomEquipe (String nomEquipe);

}
