package technocite.tn.telecite.controllers;

import java.util.ArrayList;
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
import technocite.tn.telecite.entities.Employe;
import technocite.tn.telecite.entities.Equipe;
import technocite.tn.telecite.exception.ResourceNotFoundException;
import technocite.tn.telecite.repositories.IEmploye;
import technocite.tn.telecite.repositories.IEquipe;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/telecite/equipes")
public class EquipeController {
	@Autowired
	private IEquipe equipeRepository;
	@Autowired
	private IEmploye employeRepository;

	@GetMapping("/")
	public List<Equipe> findAll() {
		List<Equipe> equipes= new ArrayList<>();
		equipeRepository.findAll().forEach(equipes::add);
		return equipes; 
	}
	@GetMapping("/{idEquipe}")
	
	public ResponseEntity findEquipeById(@PathVariable(name="idEquipe") Long idEquipe) { 
		
		  if (idEquipe == null) {
	            return ResponseEntity.badRequest().body("Cannot retrieve Equipe with null ID");
	        }
	        Optional<Equipe> equipe = equipeRepository.findById(idEquipe);
	        if (equipe == null) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok().body(equipe);
	}
	@GetMapping("employeEquipe/{idEmploye}")
	public ResponseEntity findEquipeEmploye(@PathVariable Long idEmploye) {
	    if (idEmploye == null) {
	        return ResponseEntity.badRequest().body("Cannot find equipe with null idEmploye");
	    }
	    Optional<Employe> employe = employeRepository.findById(idEmploye);
	    if (employe == null) {
	        return ResponseEntity.notFound().build();
	    }
	    Equipe equipeEmploye = equipeRepository.findByEmployes(employe);
	    
	    
	   

	    return ResponseEntity.ok(equipeEmploye);
	}
	@GetMapping("/nom/{nomEquipe}")

	public ResponseEntity findByNomEquipe(@PathVariable(name="nomEquipe") String nomEquipe) { 
		 if (nomEquipe == null) {
	            return ResponseEntity.badRequest().body("Cannot retrieve equipe with null name");
	        }
		  Equipe equipe = equipeRepository.findByNomEquipe(nomEquipe);
	        if (equipe == null) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok().body(equipe);
	}
	
	
	
	
	@PostMapping("/")
    public ResponseEntity createEquipe(@RequestBody Equipe equipe) {
        if (equipe == null) {
            return ResponseEntity.badRequest().body("Cannot create Equipe with empty fields");
        }
        Equipe createEquipe = equipeRepository.save(equipe);
        return ResponseEntity.ok(createEquipe);
    }
	
	 @PutMapping("/update/{idEquipe}")
		public ResponseEntity<Equipe> updateEquipe(@PathVariable(value = "idEquipe") Long idEquipe, @RequestBody 
				Equipe equipeDetails) throws ResourceNotFoundException {
		 Equipe equipe = equipeRepository.findById(idEquipe)
					.orElseThrow(() -> new ResourceNotFoundException("Equipe not found for this id :: " + idEquipe));
			
			equipe.setNomEquipe(equipeDetails.getNomEquipe());
			equipe.setSpecialite(equipeDetails.getSpecialite());
			equipe.setEmployes(equipeDetails.getEmployes());
			equipe.setProjets(equipeDetails.getProjets());
			
			
			
			final Equipe updateEquipe = equipeRepository.save(equipe);
			return ResponseEntity.ok(updateEquipe);
		}
	 @DeleteMapping("/{idEquipe}")
	    public ResponseEntity deleteEquipe(@PathVariable(name = "idEquipe") Long idEquipe) {
	        if (idEquipe == null) {
	            return ResponseEntity.badRequest().body("Cannot remove Equipe with null ID");
	        }
	        Equipe equipe = equipeRepository.getOne(idEquipe);
	        if (equipe == null) {
	            return  ResponseEntity.notFound().build();
	        }
	        equipeRepository.delete(equipe);
	        return ResponseEntity.ok(equipeRepository.findAll());
	    }
}
