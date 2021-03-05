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
import technocite.tn.telecite.entities.Commentaire;
import technocite.tn.telecite.entities.Projet;
import technocite.tn.telecite.entities.Sprint;
import technocite.tn.telecite.exception.ResourceNotFoundException;
import technocite.tn.telecite.repositories.ICommentaire;
import technocite.tn.telecite.repositories.ISprint;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/telecite/remarques")
public class CommentaireController {
	@Autowired
	private ICommentaire remarqueRepository;
	@Autowired
	private ISprint sprintRepository;
	
	@GetMapping("/{idCommentaire}")
	
	public ResponseEntity findProjetById(@PathVariable(name="idCommentaire") Long idCommentaire) { 
		
		  if (idCommentaire == null) {
	            return ResponseEntity.badRequest().body("Cannot retrieve commentaire with null ID");
	        }
	        Optional<Commentaire> commentaire = remarqueRepository.findById(idCommentaire);
	        if (commentaire == null) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok().body(commentaire);
	}
	@GetMapping("/remarquesSprint/{idSprint}")
    public ResponseEntity findAllRemarquesSprint(@PathVariable Long idSprint) {
        if (idSprint == null) {
            return ResponseEntity.badRequest().body("Cannot find REMARQUES with null idSprint");
        }
        Optional<Sprint> sprint = sprintRepository.findById(idSprint);
        if (sprint == null) {
            return ResponseEntity.notFound().build();
        }
        List<Commentaire> sprintRemarques = remarqueRepository.findBySprint(sprint);
        
        
       

        return ResponseEntity.ok(sprintRemarques);
    }


	@PostMapping("/")
	public ResponseEntity createRemarque(@RequestBody Commentaire commentaire) {
	if (commentaire == null) {
	    return ResponseEntity.badRequest().body("Cannot create commentaire with empty fields");
	}
	Commentaire createRemarque = remarqueRepository.save(commentaire);
	return ResponseEntity.ok(createRemarque);
	}


	@PutMapping("/update/{idCommentaire}")
	public ResponseEntity<Commentaire> updateRemarque(@PathVariable(value = "idCommentaire") Long idCommentaire, @RequestBody 
		Commentaire remarqueDetails) throws ResourceNotFoundException {
		Commentaire remarque = remarqueRepository.findById(idCommentaire)
				.orElseThrow(() -> new ResourceNotFoundException("remarque not found for this id :: " + idCommentaire));
		
		remarque.setLibelleCommentaire(remarqueDetails.getLibelleCommentaire());
		remarque.setDateCommentaire(remarqueDetails.getDateCommentaire());
		
		final Commentaire updatedRemarque = remarqueRepository.save(remarque);
		return ResponseEntity.ok(updatedRemarque);
	}

	@DeleteMapping("/{idCommentaire}")
	public ResponseEntity deleteRemarque(@PathVariable(name = "idRemarque") Long idCommentaire) {
	    if (idCommentaire == null) {
	        return ResponseEntity.badRequest().body("Cannot remove Remarque with null ID");
	    }
	    Commentaire remarque = remarqueRepository.getOne(idCommentaire);
	    if (remarque == null) {
	        return  ResponseEntity.notFound().build();
	    }
	    remarqueRepository.delete(remarque);
	    return ResponseEntity.ok(remarqueRepository.findAll());
	}
}
