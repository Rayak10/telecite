package technocite.tn.telecite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import technocite.tn.telecite.entities.Departement;

public interface IDepartement extends JpaRepository<Departement, Long> {

	Departement findByNomDepartement(String nomDepartement);

}
