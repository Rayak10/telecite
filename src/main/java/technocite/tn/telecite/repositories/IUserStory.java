package technocite.tn.telecite.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import technocite.tn.telecite.entities.Sprint;
import technocite.tn.telecite.entities.Tache;
import technocite.tn.telecite.entities.UserStory;

public interface IUserStory extends JpaRepository<UserStory, Long> {

	List<UserStory> findBySprint(Sprint sprint);
	List<UserStory> findByBacklogProduct_Projet_IdProjet(Long idProject);
	List<UserStory> findBySprint_Projet_IdProjet(Long idProjet);
	UserStory findByTachesIdTache(Long idTache);
	UserStory findByTaches_IdTache(Long idTache);
	

}
