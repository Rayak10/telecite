package technocite.tn.telecite.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import technocite.tn.telecite.entities.Equipe;
import technocite.tn.telecite.entities.Reunion;
import technocite.tn.telecite.entities.UserStory;

public interface IReunion extends JpaRepository<Reunion,Long>{

	List<Reunion> findByEquipe(Equipe equipe);
}
