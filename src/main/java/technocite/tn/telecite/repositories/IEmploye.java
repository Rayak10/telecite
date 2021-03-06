package technocite.tn.telecite.repositories;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import technocite.tn.telecite.entities.Bureau;
import technocite.tn.telecite.entities.Departement;
import technocite.tn.telecite.entities.Employe;
import technocite.tn.telecite.entities.Equipe;
import technocite.tn.telecite.entities.Reunion;
import technocite.tn.telecite.entities.Tache;
@Repository
public interface IEmploye extends JpaRepository<Employe, Long>{
	Employe findByEmailAndPassword(String email,String password);
	
	List<Employe> findByActive(Boolean active);
	
	 List<Employe> findByEquipe(Optional<Equipe> equipe);
	 
    Employe findEmployeByIdEmploye(Long idEmploye);
    
	Employe findByNomEmploye (String nomEmploye);
	
	Employe findByNomEmployeAndPrenomEmploye (String nomEmploye ,String PrenomEmploye);
	
	List<Employe> findByBureau(Bureau bureau);
	
	
	List<Employe> findByReunions(Optional<Reunion> reunion);
	
	List<Employe> findByEquipe(Equipe equipe);
	
	Employe findByEmail(String email);
	
    String findByFileName(String fileName);

	boolean existsByEmail(String email);

	boolean existsByPassword(String password);



	List<Employe> findByDepartement(Optional<Departement> departement);

	List<Employe> findByTaches(Optional<Tache> tache);


	}

