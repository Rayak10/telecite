package technocite.tn.telecite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import technocite.tn.telecite.entities.Projet;

public interface IProjet  extends JpaRepository<Projet, Long>{

}
