package technocite.tn.telecite.controllers;

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
import technocite.tn.telecite.entities.Projet;
import technocite.tn.telecite.entities.Sprint;
import technocite.tn.telecite.exception.ResourceNotFoundException;
import technocite.tn.telecite.repositories.IEmploye;
import technocite.tn.telecite.repositories.IEquipe;
import technocite.tn.telecite.repositories.IProjet;
import technocite.tn.telecite.repositories.ISprint;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/telecite/projets")
public class ProjetController {

	@Autowired
	private IProjet projetRepository;
	@Autowired
	private ISprint sprintRepository;
	@Autowired
	private IEquipe equipeRepository;
	@Autowired
	private IEmploye employeRepository;
	@GetMapping("/")
	public ResponseEntity findAll() {
		
		return  ResponseEntity.ok(projetRepository.findAll());
	}
	
	

	@GetMapping("/{idProjet}")
	
	public ResponseEntity findProjetById(@PathVariable(name="idProjet") Long idProjet) { 
		
		  if (idProjet == null) {
	            return ResponseEntity.badRequest().body("Cannot retrieve Projet with null ID");
	        }
	        Optional<Projet> projet = projetRepository.findById(idProjet);
	        if (projet == null) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok().body(projet);
	}
@GetMapping("/nom/{nomProjet}")
	
	public ResponseEntity findProjetByNom(@PathVariable(name="nomProjet") String nomProjet) { 
		
		  if (nomProjet == null) {
	            return ResponseEntity.badRequest().body("Cannot retrieve Projet with null name");
	        }
		  Projet projet = projetRepository.findByNomProjet(nomProjet);
	        if (projet == null) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok().body(projet);
	}


@GetMapping("sprintProjet/{idSprint}")
public ResponseEntity findProjetSprint(@PathVariable Long idSprint) {
    if (idSprint == null) {
        return ResponseEntity.badRequest().body("Cannot find sprint with null idSprint");
    }
    Optional<Sprint> sprint = sprintRepository.findById(idSprint);
    if (sprint == null) {
        return ResponseEntity.notFound().build();
    }
    Projet projetSprint = projetRepository.findBySprints(sprint);
    
    
   

    return ResponseEntity.ok(projetSprint);
}
@GetMapping("projetsEmploye/{idEmploye}")
public ResponseEntity findProjetsEmploye(@PathVariable Long idEmploye) {
	  if (idEmploye == null) {
	        return ResponseEntity.badRequest().body("Cannot find projet with null idEmploye");
	    }
	    Optional<Employe> employe = employeRepository.findById(idEmploye);
	    System.out.println("ttttttttttttttt"+employe.getClass().getName());
	    if (employe == null) {
	        return ResponseEntity.notFound().build();
	    }
	    Equipe equipeEmploye = equipeRepository.findByEmployes(employe);
	    System.out.println("ttttttttttttt"+equipeEmploye);
    
    
    Projet projetsEmploye= projetRepository.findByEquipe(equipeEmploye);
    
    
   

    return ResponseEntity.ok(projetsEmploye);
}


	@PostMapping("/")
    public ResponseEntity createProjet(@RequestBody Projet projet) {
        if (projet == null) {
            return ResponseEntity.badRequest().body("Cannot create projet with empty fields");
        }
    Sprint firstSprint=new Sprint();
           firstSprint.setNomSprint("Backlog produit");
           firstSprint.setNumeroSprint(1);
           firstSprint.setDateDebut(projet.getDateDebut());
           firstSprint.setDateFin(projet.getDateFin());
           firstSprint.setEtatSprint("Non terminé");
           firstSprint.setDescriptionSprint("Le product backlog de projet :"+projet.getNomProjet());
           firstSprint.setProjet(projet);
        Projet createProjet = projetRepository.save(projet);
         sprintRepository.save(firstSprint);
        return ResponseEntity.ok(createProjet);
    }
	 @PutMapping("/update/{idProjet}")
		public ResponseEntity<Projet> updateProjet(@PathVariable(value = "idProjet") Long idProjet, @RequestBody 
				Projet projetDetails) throws ResourceNotFoundException {
		 Projet projet = projetRepository.findById(idProjet)
					.orElseThrow(() -> new ResourceNotFoundException("PROJET not found for this id :: " + idProjet));
			
			projet.setNomProjet(projetDetails.getNomProjet());
			projet.setTheme(projetDetails.getTheme());
			projet.setDescription(projetDetails.getDescription());
			projet.setDescriptionTechnique(projetDetails.getDescriptionTechnique());
			projet.setDateDebut(projetDetails.getDateDebut());
			projet.setDateFin(projetDetails.getDateFin());
			projet.setEquipe(projetDetails.getEquipe());
			
			
			
			final Projet updatedProjet = projetRepository.save(projet);
			return ResponseEntity.ok(updatedProjet);
		}
	
	 @DeleteMapping("/{idProjet}")
	    public ResponseEntity deleteProjet(@PathVariable(name = "idProjet") Long idProjet) {
	        if (idProjet == null) {
	            return ResponseEntity.badRequest().body("Cannot remove projet with null ID");
	        }
	        Projet projet = projetRepository.getOne(idProjet);
	        if (projet == null) {
	            return  ResponseEntity.notFound().build();
	        }
	        projetRepository.delete(projet);
	        return ResponseEntity.ok(projetRepository.findAll());
	    }
}
