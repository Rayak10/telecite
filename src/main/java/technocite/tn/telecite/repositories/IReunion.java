package technocite.tn.telecite.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import technocite.tn.telecite.dto.ReunionDto;
import technocite.tn.telecite.entities.Equipe;
import technocite.tn.telecite.entities.Reunion;
import technocite.tn.telecite.entities.UserStory;
@Repository
public interface IReunion extends JpaRepository<Reunion,Long>{

	List<Reunion> findByEquipe(Equipe equipe);

}
