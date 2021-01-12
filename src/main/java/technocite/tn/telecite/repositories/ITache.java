package technocite.tn.telecite.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import technocite.tn.telecite.entities.Tache;
import technocite.tn.telecite.entities.UserStory;

public interface ITache extends JpaRepository<Tache, Long> {

	List<Tache> findByUserStory(UserStory userStory);
	List<Tache> findByUserStoryAndEtatTache(UserStory userStory, String etatTache);

     @Modifying
     @Transactional
	 @Query(value = "UPDATE tache set fk_emp_tache_id = :idEmploye WHERE id_tache = :idTache", 
  			  nativeQuery = true)
  			void updateTacheEmploye(@Param("idTache") long idTache ,@Param("idEmploye") long idEmploye) ;
     
     
     @Modifying
     @Transactional
	 @Query(value = "UPDATE tache set etat_tache = :etatTache WHERE id_tache = :idTache", 
 			  nativeQuery = true)
 			void updateEtatTacheEmploye(@Param("idTache") long idTache ,@Param("etatTache") String etat_tache) ;

}
