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
import technocite.tn.telecite.entities.Equipe;
import technocite.tn.telecite.entities.ReunionScrum;
import technocite.tn.telecite.exception.ResourceNotFoundException;
import technocite.tn.telecite.repositories.IEquipe;
import technocite.tn.telecite.repositories.IReunionScrum;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/telecite/reunionScrums")
public class ReunionScrumController {
	@Autowired
	private IReunionScrum reunionScrumRepository;
	@Autowired
	private IEquipe equipeRepository;
	
	@GetMapping("/")
	public ResponseEntity findAll() {
		
		return  ResponseEntity.ok(reunionScrumRepository.findAll());
	}
	

	@GetMapping("/{idReunionScrum}")
	
	public ResponseEntity findReunionScrumById(@PathVariable(name="idReunionScrum") Long idReunionScrum) { 
		
		  if (idReunionScrum == null) {
	            return ResponseEntity.badRequest().body("Cannot retrieve ReunionScrum with null ID");
	        }
	        Optional<ReunionScrum> reunionScrum = reunionScrumRepository.findById(idReunionScrum);
	        if (reunionScrum == null) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok().body(reunionScrum);
	}
	
	@PostMapping("/")
	public ResponseEntity createReunionScrum(@RequestBody ReunionScrum reunionScrum) {
    if (reunionScrum == null) {
        return ResponseEntity.badRequest().body("Cannot create ReunionScrum with empty fields");
    }
    ReunionScrum createReunionScrum = reunionScrumRepository.save(reunionScrum);
    return ResponseEntity.ok(createReunionScrum);
}
 @PutMapping("/update/{idReunionScrum}")
	public ResponseEntity<ReunionScrum> updateRieunionScrum(@PathVariable(value = "idReunionScrum") Long idReunionScrum, @RequestBody 
		ReunionScrum reunionScrumDetails) throws ResourceNotFoundException {
		ReunionScrum reunionScrum = reunionScrumRepository.findById(idReunionScrum)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + idReunionScrum));
		
		reunionScrum.setNomReunionScrum(reunionScrumDetails.getNomReunionScrum());
		reunionScrum.setDescriptionReunionScrum(reunionScrumDetails.getDescriptionReunionScrum());
		reunionScrum.setDateDebut(reunionScrumDetails.getDateDebut());
		reunionScrum.setDateFin(reunionScrumDetails.getDateFin());
		
		
		final ReunionScrum updatedReunionScrum = reunionScrumRepository.save(reunionScrum);
		return ResponseEntity.ok(updatedReunionScrum);
	}
 
 @DeleteMapping("/{idReunionScrum}")
 public ResponseEntity deleteReunionScrum(@PathVariable(name = "idReunionScrum") Long idReunionScrum) {
     if (idReunionScrum == null) {
         return ResponseEntity.badRequest().body("Cannot remove ReunionScrum with null ID");
     }
     ReunionScrum reunionScrum = reunionScrumRepository.getOne(idReunionScrum);
     if (reunionScrum == null) {
         return  ResponseEntity.notFound().build();
     }
     reunionScrumRepository.delete(reunionScrum);
     return ResponseEntity.ok(reunionScrumRepository.findAll());
 }
 
 @GetMapping("/reunionsEquipe/{nomEquipe}")
 public ResponseEntity findAllReunionsEquipe(@PathVariable String nomEquipe) {
     if (nomEquipe == null) {
         return ResponseEntity.badRequest().body("Cannot find Equipe with null nomEquipe");
     }
     Equipe equipe = equipeRepository.findByNomEquipe(nomEquipe);
     if (equipe == null) {
         return ResponseEntity.notFound().build();
     }
     List<ReunionScrum> equipeReunions = reunionScrumRepository.findByEquipe(equipe);
     
     
    

     return ResponseEntity.ok(equipeReunions);
 }

}
