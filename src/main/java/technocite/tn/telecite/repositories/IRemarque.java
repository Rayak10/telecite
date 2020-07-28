package technocite.tn.telecite.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import technocite.tn.telecite.entities.Remarque;
import technocite.tn.telecite.entities.Sprint;

public interface IRemarque extends JpaRepository<Remarque, Long> {

	List<Remarque> findBySprint(Optional<Sprint> sprint);

}
