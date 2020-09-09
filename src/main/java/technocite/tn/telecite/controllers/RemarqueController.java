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
import technocite.tn.telecite.entities.Remarque;
import technocite.tn.telecite.entities.Sprint;
import technocite.tn.telecite.exception.ResourceNotFoundException;
import technocite.tn.telecite.repositories.IRemarque;
import technocite.tn.telecite.repositories.ISprint;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/telecite/remarques")
public class RemarqueController {
	@Autowired
	private IRemarque remarqueRepository;
	@Autowired
	private ISprint sprintRepository;
	
	@GetMapping("/remarquesSprint/{idSprint}")
    public ResponseEntity findAllRemarquesSprint(@PathVariable Long idSprint) {
        if (idSprint == null) {
            return ResponseEntity.badRequest().body("Cannot find REMARQUES with null idSprint");
        }
        Optional<Sprint> sprint = sprintRepository.findById(idSprint);
        if (sprint == null) {
            return ResponseEntity.notFound().build();
        }
        List<Remarque> sprintRemarques = remarqueRepository.findBySprint(sprint);
        
        
       

        return ResponseEntity.ok(sprintRemarques);
    }


	@PostMapping("/")
	public ResponseEntity createRemarque(@RequestBody Remarque remarque) {
	if (remarque == null) {
	    return ResponseEntity.badRequest().body("Cannot create remarque with empty fields");
	}
	Remarque createRemarque = remarqueRepository.save(remarque);
	return ResponseEntity.ok(createRemarque);
	}


	@PutMapping("/update/{idRemarque}")
	public ResponseEntity<Remarque> updateRemarque(@PathVariable(value = "idRemarque") Long idRemarque, @RequestBody 
		Remarque remarqueDetails) throws ResourceNotFoundException {
		Remarque remarque = remarqueRepository.findById(idRemarque)
				.orElseThrow(() -> new ResourceNotFoundException("remarque not found for this id :: " + idRemarque));
		
		remarque.setLibelleRemarque(remarqueDetails.getLibelleRemarque());
		remarque.setDateRemarque(remarqueDetails.getDateRemarque());
		
		final Remarque updatedRemarque = remarqueRepository.save(remarque);
		return ResponseEntity.ok(updatedRemarque);
	}

	@DeleteMapping("/{idRemarque}")
	public ResponseEntity deleteRemarque(@PathVariable(name = "idRemarque") Long idRemarque) {
	    if (idRemarque == null) {
	        return ResponseEntity.badRequest().body("Cannot remove Remarque with null ID");
	    }
	    Remarque remarque = remarqueRepository.getOne(idRemarque);
	    if (remarque == null) {
	        return  ResponseEntity.notFound().build();
	    }
	    remarqueRepository.delete(remarque);
	    return ResponseEntity.ok(remarqueRepository.findAll());
	}
}
