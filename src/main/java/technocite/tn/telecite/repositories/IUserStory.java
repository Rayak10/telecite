package technocite.tn.telecite.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import technocite.tn.telecite.entities.Sprint;
import technocite.tn.telecite.entities.Tache;
import technocite.tn.telecite.entities.UserStory;

public interface IUserStory extends JpaRepository<UserStory, Long> {

	List<UserStory> findBySprint(Sprint sprint);
	List<UserStory> findByBacklogProduct_Projet_IdProjet(Long idProject);
	List<UserStory> findBySprint_Projet_IdProjet(Long idProjet);
	UserStory findByTaches_IdTache(Long idTache);
	  
    @Modifying
    @Transactional
	 @Query(value = "UPDATE user_story set fk_sprint_us_id= :idSprint WHERE id_user_story= :idUserStory", 
			  nativeQuery = true)
			void updateSprintUserStory(@Param("idUserStory") Long idUserStory ,@Param("idSprint") Long idSprint) ;
    

}
