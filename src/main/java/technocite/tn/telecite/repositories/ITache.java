package technocite.tn.telecite.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import technocite.tn.telecite.entities.Tache;
import technocite.tn.telecite.entities.UserStory;

public interface ITache extends JpaRepository<Tache, Long> {

	List<Tache> findByUserStory(UserStory userStory);

}
