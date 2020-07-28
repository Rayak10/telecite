package technocite.tn.telecite.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import technocite.tn.telecite.entities.Equipe;
import technocite.tn.telecite.entities.ReunionScrum;

public interface IReunionScrum extends JpaRepository<ReunionScrum,Long>{

	List<ReunionScrum> findByEquipe(Equipe equipe);

}
