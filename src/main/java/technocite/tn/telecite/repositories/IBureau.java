package technocite.tn.telecite.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import technocite.tn.telecite.entities.Bureau;
import technocite.tn.telecite.entities.Employe;

public interface IBureau extends JpaRepository<Bureau, Long>{


	Bureau findByEmployes(Optional<Employe> employe);

	Bureau findByNomBureau(String nomBureau);






}
