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
import technocite.tn.telecite.exception.ResourceNotFoundException;
import technocite.tn.telecite.repositories.IProjet;
import technocite.tn.telecite.repositories.ISprint;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/telecite/sprints")
public class SprintController {
	@Autowired
	private ISprint sprintRepository;
	 @Autowired
	    private IProjet projetRepository;
	
	@GetMapping("/")
	public ResponseEntity findAll() {
		
		return  ResponseEntity.ok(sprintRepository.findAll());
	}
	
	
	 @GetMapping("/all/{idProjet}")
	    public ResponseEntity findAllsprintsProjet(@PathVariable Long idProjet) {
	        if (idProjet == null) {
	            return ResponseEntity.badRequest().body("Cannot find sprints with null Projet");
	        }
	        Projet projet = projetRepository.getOne(idProjet);
	        if (projet == null) {
	            return ResponseEntity.notFound().build();
	        }
	        List<Sprint> projetSprints = sprintRepository.findByProjet(projet);
	        
	        
	       // projetSprint.forEach(sprint -> sprint.setIdOwner(idProjet));
	       

	        return ResponseEntity.ok(projetSprints);
	    }
	    
	
	
@GetMapping("/{idSprint}")
	
	public ResponseEntity findSprintById(@PathVariable(name="idSprint") Long idSprint) { 
		
		  if (idSprint == null) {
	            return ResponseEntity.badRequest().body("Cannot retrieve Employe with null ID");
	        }
	        Optional<Sprint> sprint = sprintRepository.findById(idSprint);
	        if (sprint == null) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok().body(sprint);
	          
	}
@GetMapping("/etat/{etatSprint}")

public ResponseEntity findByEtatSprint(@PathVariable(name="etatSprint") String etatSprint) { 
	
	  if (etatSprint == null) {
            return ResponseEntity.badRequest().body("Cannot retrieve sprint with null etat");
        }
	  List<Sprint> sprints = sprintRepository.findByEtatSprint(etatSprint);
        if (sprints == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(sprints);
          
}

@PostMapping("/")

    public ResponseEntity createSprint(@RequestBody Sprint sprint) {
	
        if (sprint == null) {
        	
          return ResponseEntity.badRequest().body("Cannot create Sprint with empty fields");
      }
          Sprint createSprint = sprintRepository.save(sprint);
          return ResponseEntity.ok(createSprint);
}
	

@PutMapping("/update/{idSprint}")
public ResponseEntity<Sprint> updateSprint(@PathVariable(value = "idSprint") Long idSprint, @RequestBody 
		Sprint sprintDetails) throws ResourceNotFoundException {
	Sprint sprint = sprintRepository.findById(idSprint)
			.orElseThrow(() -> new ResourceNotFoundException("Sprint not found for this id :: " + idSprint));
	
	sprint.setNomSprint(sprintDetails.getNomSprint());
	sprint.setNumeroSprint(sprintDetails.getNumeroSprint());
	sprint.setDateDebut(sprintDetails.getDateDebut());
	sprint.setDateFin(sprintDetails.getDateFin());
	sprint.setEtatSprint(sprintDetails.getEtatSprint());
	
	
	final Sprint updatedSprint = sprintRepository.save(sprint);
	return ResponseEntity.ok(updatedSprint);
}


@DeleteMapping("/{idSprint}")
public ResponseEntity deleteSprint(@PathVariable(name = "idSprint") Long idSprint) {
    if (idSprint == null) {
        return ResponseEntity.badRequest().body("Cannot remove Sprint with null ID");
    }
    Sprint sprint = sprintRepository.getOne(idSprint);
    if (sprint == null) {
        return  ResponseEntity.notFound().build();
    }
    sprintRepository.delete(sprint);
    return ResponseEntity.ok(sprintRepository.findAll());
}

}
