package technocite.tn.telecite.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import technocite.tn.telecite.dto.ReunionDto;
import technocite.tn.telecite.entities.Equipe;
import technocite.tn.telecite.entities.Reunion;
import technocite.tn.telecite.entities.UserStory;
import technocite.tn.telecite.enums.ReunionType;
@Repository
public interface IReunion extends JpaRepository<Reunion,Long>{

	List<Reunion> findByEquipe(Equipe equipe);

	List<Reunion> findByType(ReunionType type);
	   
   


}
