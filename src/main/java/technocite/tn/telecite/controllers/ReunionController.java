package technocite.tn.telecite.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
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
import technocite.tn.telecite.dto.TimeDTO;
import technocite.tn.telecite.entities.Employe;
import technocite.tn.telecite.entities.Equipe;
import technocite.tn.telecite.entities.Reunion;
import technocite.tn.telecite.enums.ReunionType;
import technocite.tn.telecite.exception.ResourceNotFoundException;
import technocite.tn.telecite.repositories.IEmploye;
import technocite.tn.telecite.repositories.IEquipe;
import technocite.tn.telecite.repositories.IReunion;
import technocite.tn.telecite.repositories.ReunionService;

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
	        Reunion reunion=null;

	        
	        
	        if(reunionScrum.isPresent()) {
	        reunion = reunionScrum.get();

	        reunion.setHeurDeb(reunion.getHeurDeb().minusHours(1));
	        reunion.setHeurFin(reunion.getHeurFin().minusHours(1));

	        }
	        
	        return ResponseEntity.ok().body(reunion);
	}
	
	@GetMapping("/dto/{idReunion}")
	
	public ResponseEntity findReunionDtoById(@PathVariable(name="idReunion") Long idReunion) { 
		
		  if (idReunion == null) {
	            return ResponseEntity.badRequest().body("Cannot retrieve Reunion with null ID");
	        }
		  
	        Optional<ReunionDto> reunionScrum = reunionService.findById(idReunion);
	        
	        if (reunionScrum == null) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok().body(reunionScrum);
	}

	 @PostMapping("/")
	    public ResponseEntity<ReunionDto> addReunions(@RequestBody ReunionDto reunionDto) {
	        ReunionDto rdto = reunionService.addReunion(reunionDto);
	        return new ResponseEntity<>(rdto, HttpStatus.CREATED);
	    }
	
	  @PutMapping("/updateReunion/{idReunion}")
	    public ResponseEntity<ReunionDto> updateRieunion(@PathVariable(name = "idReunion") Long idReunion,
	            @RequestBody ReunionDto reunion) {
	        ReunionDto rdto = reunionService.updateReunion(idReunion, reunion);
	        return new ResponseEntity<>(rdto, HttpStatus.CREATED);
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
 
 private ReunionDto mapEntityToDto(Reunion reunion) {
 	ReunionDto responseDto = new ReunionDto();
     responseDto.setIdReunion(reunion.getIdReunion());
     responseDto.setNomReunion(reunion.getNomReunion());
     responseDto.setDateFin(reunion.getDateFin());
     responseDto.setDescriptionReunion(reunion.getDescriptionReunion());
     responseDto.setEquipe(reunion.getEquipe());
     responseDto.setHeurDeb(reunion.getHeurDeb().minusHours(1));
     responseDto.setHeurFin(reunion.getHeurFin().minusHours(1));
	 System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");

     responseDto.setDateDebut(reunion.getDateDebut());
     responseDto.setType(reunion.getType());
 responseDto.setEmployes(reunion.getEmployes().stream().map(Employe::getIdEmploye).collect(Collectors.toSet()));
     return responseDto;
 }

 @GetMapping("/reunionsType/{type}")
 public ResponseEntity findAllReunionsType(@PathVariable ReunionType type) {
     if (type == null) {
         return ResponseEntity.badRequest().body("Cannot find reunions with null type");
     }
     List<Reunion> typeReunions = reunionRepository.findByType(type);
		typeReunions.forEach(reunion -> {
			
		});
     
     
    List<ReunionDto> dtos=new ArrayList<>();
    typeReunions.forEach(r-> dtos.add(mapEntityToDto(r)));

    return ResponseEntity.ok(dtos);
 }

 }

