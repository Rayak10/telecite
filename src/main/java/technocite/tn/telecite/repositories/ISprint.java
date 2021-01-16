package technocite.tn.telecite.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import technocite.tn.telecite.entities.Equipe;
import technocite.tn.telecite.entities.Projet;
import technocite.tn.telecite.entities.Sprint;

public interface ISprint  extends JpaRepository<Sprint, Long>{
	
	 List<Sprint> findByEtatSprint(String etatSprint);
	List<Sprint> findByProjet(Optional<Projet> projet);
	 List<Sprint> findByOrderByProjetAsc();
	Equipe findByProjet_Equipe(Long idSprint);
	Sprint findByNomSprint(String nomSprint);

    @Transactional
	 @Query(value = "SELECT COUNT(*) FROM sprint where `fk_prj_sprint_id`= :idProjet", 
			  nativeQuery = true)
			int nembreSprint(@Param("idProjet") long idProjet ) ;


	
    @Transactional	 
    @Query(value = " SELECT * FROM sprint WHERE fk_prj_sprint_id=:idProjet and DATE(NOW())>=DATE(date_debut) and DATE(NOW())<=DATE(date_fin)OR DATE(NOW())>=DATE(date_fin)ORDER BY DATE(date_fin) DESC ",
		  nativeQuery = true)
		List<Sprint>sprintsProgress(@Param("idProjet") long idProjet) ;
}
    
//    @Transactional	 
//    @Query(value = " SELECT  id_sprint from sprint WHERE fk_prj_sprint_id=:idProjet and nom_sprint = 'Backlog produit'",
//		  nativeQuery = true)
//		List<Sprint>sprintsBacklog(@Param("idProjet") long idProjet) ;
//
//    	}

