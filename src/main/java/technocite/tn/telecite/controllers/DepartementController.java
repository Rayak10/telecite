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
import technocite.tn.telecite.entities.Departement;
import technocite.tn.telecite.entities.Employe;
import technocite.tn.telecite.exception.ResourceNotFoundException;
import technocite.tn.telecite.repositories.IDepartement;
import technocite.tn.telecite.repositories.IEmploye;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/telecite/departements")
public class DepartementController {
	@Autowired
	private IDepartement departementRepository;
	@Autowired
	private IEmploye employeRepository;
	
@GetMapping("/")
	public ResponseEntity findAll() {
		
		return  ResponseEntity.ok(departementRepository.findAll());
	}
@GetMapping("/{idDepartement}")
	
	public ResponseEntity findDepartementById(@PathVariable(name="idDepartement") Long idDepartement) { 
		
		  if (idDepartement == null) {
	            return ResponseEntity.badRequest().body("Cannot retrieve Departement with null ID");
	        }
	        Optional<Departement> departement = departementRepository.findById(idDepartement);
	        if (departement == null) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok().body(departement);
	}
@GetMapping("employeDepartement/{idEmploye}")
public ResponseEntity findBureuEmploye(@PathVariable Long idEmploye) {
    if (idEmploye == null) {
        return ResponseEntity.badRequest().body("Cannot find bureau with null idEmploye");
    }
    Optional<Employe> employe = employeRepository.findById(idEmploye);
    if (employe == null) {
        return ResponseEntity.notFound().build();
    }
    Departement departementEmploye = departementRepository.findByEmployes(employe);
    
    
   

    return ResponseEntity.ok(departementEmploye);
}

@GetMapping("/nom/{nomDepartement}")

public ResponseEntity findByNomDepartement(@PathVariable(name="nomDepartement") String nomDepartement) { 
	 if (nomDepartement == null) {
            return ResponseEntity.badRequest().body("Cannot retrieve Departement with null name");
        }
	 Departement departement = departementRepository.findByNomDepartement(nomDepartement);
        if (departement == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(departement);
}

@PostMapping("/")
public ResponseEntity createDepartement(@RequestBody Departement departement) {
if (departement == null) {
    return ResponseEntity.badRequest().body("Cannot create Departement with empty fields");
}
Departement createDepartement = departementRepository.save(departement);
return ResponseEntity.ok(createDepartement);
}


@PutMapping("/update/{idDepartement}")
public ResponseEntity<Departement> updateDepartement(@PathVariable(value = "idDepartement") Long idDepartement, @RequestBody 
	Departement departementDetails) throws ResourceNotFoundException {
	Departement departement = departementRepository.findById(idDepartement)
			.orElseThrow(() -> new ResourceNotFoundException("buraeu not found for this id :: " + idDepartement));
	
	departement.setNomDepartement(departementDetails.getNomDepartement());
	
	final Departement updatedDepartement = departementRepository.save(departement);
	return ResponseEntity.ok(updatedDepartement);
}

@DeleteMapping("/{idDepartement}")
public ResponseEntity deleteDepartement(@PathVariable(name = "idDepartement") Long idDepartement) {
    if (idDepartement == null) {
        return ResponseEntity.badRequest().body("Cannot remove Departement with null ID");
    }
    Departement departement = departementRepository.getOne(idDepartement);
    if (departement == null) {
        return  ResponseEntity.notFound().build();
    }
    departementRepository.delete(departement);
    return ResponseEntity.ok(departementRepository.findAll());
}
}
