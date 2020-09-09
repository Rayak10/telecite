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
import technocite.tn.telecite.entities.Tache;
import technocite.tn.telecite.entities.UserStory;
import technocite.tn.telecite.exception.ResourceNotFoundException;
import technocite.tn.telecite.repositories.IEmploye;
import technocite.tn.telecite.repositories.ITache;
import technocite.tn.telecite.repositories.IUserStory;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/telecite/taches")
public class TacheController {

	
@Autowired
	private ITache tacheRepository;
@Autowired
	    private IUserStory userStoryRepository;
@Autowired
	    private IEmploye employeRepository;
		
		
		@GetMapping("/")
		public ResponseEntity findAll() {
		
		return  ResponseEntity.ok(tacheRepository.findAll());
		}
	
	
   		@GetMapping("/all/{idUserStory}")
	    public ResponseEntity findAllTachesStories(@PathVariable Long idUserStory) {
	        if (idUserStory == null) {
	            return ResponseEntity.badRequest().body("Cannot find taches with null UserStory");
	        }
	        UserStory userStory = userStoryRepository.getOne(idUserStory);
	        if (userStory == null) {
	            return ResponseEntity.notFound().build();
	        }
	        List<Tache> storieTaches = tacheRepository.findByUserStory(userStory);
	        
	        
	       // projetSprint.forEach(sprint -> sprint.setIdOwner(idProjet));
	       

	        return ResponseEntity.ok(storieTaches);
	    }
	    
	
	
   		@GetMapping("/{idTache}")
   		public ResponseEntity findTacheById(@PathVariable(name="idTache") Long idTache) { 
   			
   			if (idTache == null) {
	            return ResponseEntity.badRequest().body("Cannot retrieve Tache with null ID");
	        }
	        Optional<Tache> tache = tacheRepository.findById(idTache);
	        if (tache == null) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok().body(tache);
	          
   		}	


   		@PostMapping("/")
   		public ResponseEntity createTache(@RequestBody Tache tache) {
	
   			if (tache == null) {
        	
          return ResponseEntity.badRequest().body("Cannot create tache with empty fields");
        	}
        tache.setEtatTache("to do");
        Tache creatTache = tacheRepository.save(tache);
          return ResponseEntity.ok(creatTache);
   		}
	

   		@PutMapping("/update/{idTache}")
   		public ResponseEntity<Tache> updateSprint(@PathVariable(value = "idTache") Long idTache, @RequestBody 
			Tache tacheDetails) throws ResourceNotFoundException {
   			Tache tache = tacheRepository.findById(idTache)
			.orElseThrow(() -> new ResourceNotFoundException("Tache not found for this id :: " + idTache));
	
		tache.setDescriptionTache(tacheDetails.getDescriptionTache());
		tache.setDureeTache(tacheDetails.getDureeTache());
		tache.setEtatTache(tacheDetails.getEtatTache());
	
	
		final Tache updateTache = tacheRepository.save(tache);
		return ResponseEntity.ok(updateTache);
   		}


   		@DeleteMapping("/{idTache}")
   		public ResponseEntity deleteSprint(@PathVariable(name = "idTache") Long idTache) {
   			if (idTache == null) {
        return ResponseEntity.badRequest().body("Cannot remove Sprint with null ID");
   			}
   			Tache tache = tacheRepository.getOne(idTache);
   			if (tache == null) {
        return  ResponseEntity.notFound().build();
   			}
   			tacheRepository.delete(tache);
   			return ResponseEntity.ok(tacheRepository.findAll());
   		}

}
