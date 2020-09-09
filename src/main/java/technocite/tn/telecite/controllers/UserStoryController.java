package technocite.tn.telecite.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import technocite.tn.telecite.entities.Projet;
import technocite.tn.telecite.entities.Sprint;
import technocite.tn.telecite.entities.UserStory;
import technocite.tn.telecite.exception.ResourceNotFoundException;
import technocite.tn.telecite.repositories.IProjet;
import technocite.tn.telecite.repositories.ISprint;
import technocite.tn.telecite.repositories.IUserStory;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/telecite/userStorys")
public class UserStoryController {
@Autowired
	private ISprint sprintRepository;
@Autowired
	    private IUserStory userStoryRepository;
@Autowired
private IProjet projetRepository;

@GetMapping("/")
		public ResponseEntity findAll() {
			
			return  ResponseEntity.ok(userStoryRepository.findAll());
		}
		
		
@GetMapping("/sprint/{idSprint}")
		    public ResponseEntity findAllStoriessprint(@PathVariable Long idSprint) {
		        if (idSprint == null) {
		            return ResponseEntity.badRequest().body("Cannot find stories with null Sprint");
		        }
		        Sprint sprint = sprintRepository.getOne(idSprint);
		        if (sprint == null) {
		            return ResponseEntity.notFound().build();
		        }
		        List<UserStory> userstorysprint = userStoryRepository.findBySprint(sprint);
		        
		       

		        return ResponseEntity.ok(userstorysprint);
		    }
		    
		
		
@GetMapping("/{idUserStory}")
		
		public ResponseEntity findUserstoryById(@PathVariable(name="idUserStory") Long idUserStory) { 
			
			  if (idUserStory == null) {
		            return ResponseEntity.badRequest().body("Cannot retrieve UserStory with null ID");
		        }
		        Optional<UserStory> userStory = userStoryRepository.findById(idUserStory);
		        if (userStory == null) {
		            return ResponseEntity.notFound().build();
		        }
		        return ResponseEntity.ok().body(userStory);
		          
		}
@GetMapping("userstorysProjet/{idProjet}")

public ResponseEntity findUserStorysByProjet(@PathVariable(name="idProjet") Long idProjet) { 
	
    List<UserStory> userstorysprintsprojet = userStoryRepository.findBySprint_Projet_IdProjet(idProjet);
	  /*if (idProjet == null) {
            return ResponseEntity.badRequest().body("Cannot retrieve UserStory with null ID");
        }
        Optional<Projet> projet = projetRepository.findById(idProjet);
        System.out.println(projet);
        if (projet == null) {
            return ResponseEntity.notFound().build();
        }
        List<Sprint> projetSprints = sprintRepository.findByProjet(projet);
        System.out.println(projetSprints);

        List<UserStory> userstorysprintsprojet = null;
    
        
for( Sprint sprint : projetSprints) {
	 userstorysprintsprojet =userStoryRepository.findBySprint(sprint);
	}*/
	  

return ResponseEntity.ok().body(userstorysprintsprojet);

}

@GetMapping("userstoryTache/{idTache}")

public ResponseEntity findUserStorysByTache(@PathVariable(name="idTache") Long idTache) { 
	
    UserStory userstorys = userStoryRepository.findByTaches_IdTache(idTache);
	 
return ResponseEntity.ok().body(userstorys);

}

@PostMapping("/")

	    public ResponseEntity createUserStory(@RequestBody UserStory userStory) {
		
	        if (userStory == null) {
	        	
	          return ResponseEntity.badRequest().body("Cannot create userStory with empty fields");
	      }
	        UserStory createUserStory = userStoryRepository.save(userStory);
	          return ResponseEntity.ok(createUserStory);
	}
		

@PutMapping("/update/{idUserStory}")
	public ResponseEntity<UserStory> updateUserStory(@PathVariable(value = "idUserStory") Long idUserStory, @RequestBody 
			UserStory userStoryDetails) throws ResourceNotFoundException {
	UserStory userStory = userStoryRepository.findById(idUserStory)
				.orElseThrow(() -> new ResourceNotFoundException("Sprint not found for this id :: " + idUserStory));
		
	userStory.setLibelleUserStory(userStoryDetails.getLibelleUserStory());
	userStory.setPriorite(userStoryDetails.getPriorite());
	userStory.setComplexite(userStoryDetails.getComplexite());
	userStory.setSprint(userStoryDetails.getSprint());
		
		final UserStory updatedUserStory = userStoryRepository.save(userStory);
		return ResponseEntity.ok(updatedUserStory);
	}


@DeleteMapping("/{idUserStory}")
	public ResponseEntity deleteUserStory(@PathVariable(name = "idUserStory") Long idUserStory) {
	    if (idUserStory == null) {
	        return ResponseEntity.badRequest().body("Cannot remove UserStory with null ID");
	    }
	    UserStory userStory = userStoryRepository.getOne(idUserStory);
	    if (userStory == null) {
	        return  ResponseEntity.notFound().build();
	    }
	    userStoryRepository.delete(userStory);
	    return ResponseEntity.ok(userStoryRepository.findAll());
	}

}
