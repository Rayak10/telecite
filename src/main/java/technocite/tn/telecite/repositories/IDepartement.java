package technocite.tn.telecite.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import technocite.tn.telecite.entities.Departement;
import technocite.tn.telecite.entities.Employe;

public interface IDepartement extends JpaRepository<Departement, Long> {

	Departement findByNomDepartement(String nomDepartement);

	Departement findByEmployes(Optional<Employe> employe);



}
