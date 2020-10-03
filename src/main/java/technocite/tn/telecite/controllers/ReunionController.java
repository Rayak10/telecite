package technocite.tn.telecite.controllers;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import technocite.tn.telecite.dto.ReunionDto;
import technocite.tn.telecite.dto.ReunionService;
import technocite.tn.telecite.entities.Employe;
import technocite.tn.telecite.entities.Equipe;
import technocite.tn.telecite.entities.Reunion;
import technocite.tn.telecite.exception.ResourceNotFoundException;
import technocite.tn.telecite.repositories.IEmploye;
import technocite.tn.telecite.repositories.IEquipe;
import technocite.tn.telecite.repositories.IReunion;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/telecite/reunions")
public class ReunionController {
	@Autowired
	private ReunionService reunionService;
	@Autowired
	private  ModelMapper modelMapper;
	@Autowired
	private  IEmploye employeservice;
	@Autowired
	private IReunion reunionRepository;
	@Autowired
	private IEquipe equipeRepository;
	
	@GetMapping("/")
	public ResponseEntity findAll() {
		
		return  ResponseEntity.ok(reunionRepository.findAll());
	}
	

	@GetMapping("/{idReunion}")
	
	public ResponseEntity findReunionById(@PathVariable(name="idReunion") Long idReunion) { 
		
		  if (idReunion == null) {
	            return ResponseEntity.badRequest().body("Cannot retrieve Reunion with null ID");
	        }
	        Optional<Reunion> reunionScrum = reunionRepository.findById(idReunion);
	        if (reunionScrum == null) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok().body(reunionScrum);
	}
	//
	//@PostMapping("/")
	//public ResponseEntity createReunion(@RequestBody ReunionDto reunionDto) {
	//	System.out.println("dtoùùùùù: "+ reunionDto);
	
	//	System.out.println("entity: £££££"+reunion);
	//	return ResponseEntity.ok("ok");
    //if (reunion == null) {
    //    return ResponseEntity.badRequest().body("Cannot create Reunion   with empty fields");
   // }
  
	//reunion.setHeurDeb(LocalTime.of(reunion.getHeureDeb().getHour()+1, reunion.getHeureDeb().getMinute(),00));
	//reunion.setHeurFin(LocalTime.of(reunion.getHeureFin().getHour()+1, reunion.getHeureFin().getMinute(),00));
	//reunion.getEmployes().forEach(emp->reunion.addEmployes(emp));
	//System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+reunion);

	//  Reunion createReunion = reunionRepository.save(reunion);
	  //  return ResponseEntity.ok(createReunion);
		//ReunionDto rund = ReunionService.class
	   //     return new ResponseEntity<>(rund, HttpStatus.CREATED);
	  
	//}
	 @PostMapping("/")
	    public ResponseEntity<ReunionDto> getAllReunions(@RequestBody ReunionDto reunionDto) {
		 
		// reunionDto.setHeurDeb(LocalTime.of(reunionDto.getHeureDeb().getHour()+1,reunionDto.getHeureDeb().getMinute(),00));
		 //reunionDto.setHeurFin(LocalTime.of(reunionDto.getHeureFin().getHour()+1,reunionDto.getHeureFin().getMinute(),00));

	        ReunionDto std = reunionService.addReunion(reunionDto);
	        return new ResponseEntity<>(std, HttpStatus.CREATED);
	    }
 @PutMapping("/update/{idReunion}")
	public ResponseEntity<Reunion> updateRieunion(@PathVariable(value = "idReunion") Long idReunion, @RequestBody 
		Reunion reunionDetails) throws ResourceNotFoundException {
		Reunion reunion = reunionRepository.findById(idReunion)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + idReunion));
		
		reunion.setNomReunion(reunionDetails.getNomReunion());
		reunion.setDescriptionReunion(reunionDetails.getDescriptionReunion());
		reunion.setDateDebut(reunionDetails.getDateDebut());
		reunion.setDateFin(reunionDetails.getDateFin());
		reunion.setNotification(reunionDetails.getNotification());
		reunion.setConversation(reunionDetails.getConversation());
		reunion.setEquipe(reunionDetails.getEquipe());
		reunion.setType(reunionDetails.getType());
		final Reunion updatedReunion = reunionRepository.save(reunion);
		return ResponseEntity.ok(updatedReunion);
	}
 
 @DeleteMapping("/{idReunion}")
 public ResponseEntity deleteReunion(@PathVariable(name = "idReunion") Long idReunion) {
     if (idReunion== null) {
         return ResponseEntity.badRequest().body("Cannot remove Reunionwith null ID");
     }
     Reunion reunion= reunionRepository.getOne(idReunion);
     if (reunion == null) {
         return  ResponseEntity.notFound().build();
     }
     reunionRepository.delete(reunion);
     return ResponseEntity.ok(reunionRepository.findAll());
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
     List<Reunion> equipeReunions = reunionRepository.findByEquipe(equipe);
     
     
    

     return ResponseEntity.ok(equipeReunions);
 }

}
