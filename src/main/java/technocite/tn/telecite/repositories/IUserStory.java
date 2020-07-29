package technocite.tn.telecite.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import technocite.tn.telecite.entities.Sprint;
import technocite.tn.telecite.entities.UserStory;

public interface IUserStory extends JpaRepository<UserStory, Long> {

	List<UserStory> findBySprint(Sprint sprint);

}
