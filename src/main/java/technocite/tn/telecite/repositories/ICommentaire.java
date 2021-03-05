package technocite.tn.telecite.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import technocite.tn.telecite.entities.Commentaire;
import technocite.tn.telecite.entities.Sprint;

public interface ICommentaire extends JpaRepository<Commentaire, Long> {

	List<Commentaire> findBySprint(Optional<Sprint> sprint);

}
