package technocite.tn.telecite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import technocite.tn.telecite.entities.Postit;

public interface IDepartement extends JpaRepository<Postit, Long> {

}
