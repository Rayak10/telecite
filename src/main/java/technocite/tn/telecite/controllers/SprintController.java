package technocite.tn.telecite.controllers;

import java.time.LocalTime;
import java.util.Date;
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
import technocite.tn.telecite.entities.Projet;
import technocite.tn.telecite.entities.Reunion;
import technocite.tn.telecite.entities.Sprint;
import technocite.tn.telecite.enums.ReunionType;
import technocite.tn.telecite.exception.ResourceNotFoundException;
import technocite.tn.telecite.repositories.IProjet;
import technocite.tn.telecite.repositories.IReunion;
import technocite.tn.telecite.repositories.ISprint;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/telecite/sprints")
public class SprintController {
	@Autowired
	private ISprint sprintRepository;
	 @Autowired
	    private IProjet projetRepository;
	 @Autowired
	    private IReunion reunionRepository;
	@GetMapping("/")
	public ResponseEntity findAll() {
		
		return  ResponseEntity.ok(sprintRepository.findAll());
	}

	@GetMapping("/orderByprojets/")
	public ResponseEntity findAllOrderByProjet() {
		
		return  ResponseEntity.ok(sprintRepository.findByOrderByProjetAsc());
	}
	
	
	 @GetMapping("/projet/{idProjet}")
	    public ResponseEntity findAllSprintsProjet(@PathVariable Long idProjet) {
	        if (idProjet == null) {
	            return ResponseEntity.badRequest().body("Cannot find sprints with null idProjet");
	        }
	        Optional<Projet> projet = projetRepository.findById(idProjet);
	        if (projet == null) {
	            return ResponseEntity.notFound().build();
	        }
	        List<Sprint> projetSprints = sprintRepository.findByProjet(projet);
	        
	        
	       

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
@GetMapping("backProduit/{idProjet}")

public ResponseEntity findSprintBpByIdProjet(@PathVariable(name="idProjet") Long idProjet) { 
	
	  if (idProjet == null) {
            return ResponseEntity.badRequest().body("Cannot retrieve Employe with null ID");
        }
        Optional<Projet> projet = projetRepository.findById(idProjet);
        if (projet == null) {
            return ResponseEntity.notFound().build();
        }
        
        List<Sprint> projetSprints = sprintRepository.findByProjet(projet);
        

        Sprint sprintCherche=null;
        
for( Sprint sprint : projetSprints) {
   if(sprint.getNomSprint().equals("Backlog produit")) {
	   sprintCherche=sprint;
	   break;
	   }
	  
}
return ResponseEntity.ok().body(sprintCherche);

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
       
        Reunion  reunionPlanification =new Reunion();
        reunionPlanification.setNomReunion("Réunion de planification");
        reunionPlanification.setDateDebut(sprint.getDateDebut());
        reunionPlanification.setDateFin(sprint.getDateDebut());
        reunionPlanification.setDescriptionReunion("Réunion de planification du sprint "+sprint.getNomSprint());
        reunionPlanification.setHeurDeb(LocalTime.of(10, 00,00));
        reunionPlanification.setHeurFin(LocalTime.of(19, 00,00));
        reunionPlanification.setType(ReunionType.ReunionScrum);
        reunionPlanification.setEquipe(sprint.getProjet().getEquipe());
        Reunion dalyScrum=new Reunion();
        dalyScrum.setNomReunion("Réunion DalyScrum");
        dalyScrum.setDateDebut(new Date(sprint.getDateDebut().getTime()+(3600000*24)));
        dalyScrum.setDateFin(new Date(sprint.getDateFin().getTime()-3600000*24));
        dalyScrum.setDescriptionReunion("Réunion DalyScrum du sprint "+sprint.getNomSprint());
        dalyScrum.setHeurDeb(LocalTime.of(10, 00,00));
        dalyScrum.setHeurFin(LocalTime.of(10, 15,00));

        dalyScrum.setType(ReunionType.ReunionScrum);
        dalyScrum.setEquipe(sprint.getProjet().getEquipe());
        
        
        Reunion  reunionretrospective =new Reunion();
        reunionretrospective.setNomReunion("Réunion rétrospective");
        reunionretrospective.setDateDebut(sprint.getDateFin());
        reunionretrospective.setDateFin(sprint.getDateFin());
        reunionretrospective.setHeurDeb(LocalTime.of(10, 00,00));
        reunionretrospective.setHeurFin(LocalTime.of(19, 00,00));
        reunionretrospective.setDescriptionReunion("Réunion rétrospective du sprint "+sprint.getNomSprint());;
        reunionretrospective.setType(ReunionType.ReunionScrum);
        reunionretrospective.setEquipe(sprint.getProjet().getEquipe());
        Reunion  reunionreview =new Reunion();
        reunionreview.setNomReunion("Réunion sprint review");
        reunionreview.setDateDebut(sprint.getDateFin());
        reunionreview.setDateFin(sprint.getDateFin());
        reunionreview.setHeurDeb(LocalTime.of(10, 00,00));
        reunionreview.setHeurFin(LocalTime.of(19, 00,00));
        reunionreview.setDescriptionReunion("Réunion sprint review du sprint "+sprint.getNomSprint());;
        reunionreview.setType(ReunionType.ReunionScrum);
        reunionreview.setEquipe(sprint.getProjet().getEquipe());
        Sprint createSprint = sprintRepository.save(sprint);
        reunionRepository.save(reunionPlanification);
        reunionRepository.save(dalyScrum);
      reunionRepository.save(reunionreview);
      reunionRepository.save(reunionretrospective);
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
	sprint.setDescriptionSprint(sprintDetails.getDescriptionSprint());
	sprint.setProjet(sprintDetails.getProjet());
	
	
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
