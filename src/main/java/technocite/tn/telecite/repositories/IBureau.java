package technocite.tn.telecite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import technocite.tn.telecite.entities.Bureau;

public interface IBureau extends JpaRepository<Bureau, Long>{

	Bureau findByNomBureau(String nomBureau);

}
