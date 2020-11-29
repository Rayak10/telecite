package technocite.tn.telecite.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import technocite.tn.telecite.entities.Employe;
import technocite.tn.telecite.entities.Equipe;
import technocite.tn.telecite.entities.Reunion;
import technocite.tn.telecite.enums.ReunionType;

@Repository
public interface IReunion extends JpaRepository<Reunion, Long> {

	List<Reunion> findByEquipe(Equipe equipe);

	List<Reunion> findByType(ReunionType type);

	@Query(value = "SELECT * FROM reunion r WHERE  DATE_FORMAT(r.date_debut,'%Y-%m-%d') = DATE_FORMAT(NOW(),'%Y-%m-%d')", nativeQuery = true)
	List<Reunion> findByDateDebut();
	
	List<Employe> findEmployesByIdReunion(Long idReunion);

}
