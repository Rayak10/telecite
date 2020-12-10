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

import technocite.tn.telecite.entities.Bureau;
import technocite.tn.telecite.entities.Employe;
import technocite.tn.telecite.exception.ResourceNotFoundException;
import technocite.tn.telecite.repositories.IBureau;
import technocite.tn.telecite.repositories.IEmploye;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/telecite/bureaux")
public class BureauController {
	@Autowired
	private IBureau bureauRepository;
	@Autowired
	private IEmploye employeRepository;
	
	
	
@GetMapping("/")
	public ResponseEntity findAll() {
		
		return  ResponseEntity.ok(bureauRepository.findAll());
	}
@GetMapping("/{idBureau}")
	
	public ResponseEntity findBureauById(@PathVariable(name="idBureau") Long idBureau) { 
		
		  if (idBureau == null) {
	            return ResponseEntity.badRequest().body("Cannot retrieve Bureau with null ID");
	        }
	        Optional<Bureau> bureau = bureauRepository.findById(idBureau);
	        if (bureau == null) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok().body(bureau);
	}
@GetMapping("employeBureau/{idEmploye}")
public ResponseEntity findBureuEmploye(@PathVariable Long idEmploye) {
    if (idEmploye == null) {
        return ResponseEntity.badRequest().body("Cannot find bureau with null idEmploye");
    }
    Optional<Employe> employe = employeRepository.findById(idEmploye);
    if (employe == null) {
        return ResponseEntity.notFound().build();
    }
    Bureau bureauEmploye = bureauRepository.findByEmployes(employe);
    
    
   

    return ResponseEntity.ok(bureauEmploye);
}

@GetMapping("/nom/{nomBureau}")

public ResponseEntity findByNomBureau(@PathVariable(name="nomBureau") String nomBureau) { 
	 if (nomBureau == null) {
            return ResponseEntity.badRequest().body("Cannot retrieve bureau with null name");
        }
	  Bureau bureau = bureauRepository.findByNomBureau(nomBureau);
        if (bureau == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(bureau);
}


@PostMapping("/")
public ResponseEntity createBureau(@RequestBody Bureau bureau) {
if (bureau == null) {
    return ResponseEntity.badRequest().body("Cannot create bureau with empty fields");
}
Bureau createBureau = bureauRepository.save(bureau);
return ResponseEntity.ok(createBureau);
}


@PutMapping("/update/{idBureau}")
public ResponseEntity<Bureau> updateBureau(@PathVariable(value = "idBureau") Long idBureau, @RequestBody 
	Bureau bureauDetails) throws ResourceNotFoundException {
	Bureau bureau = bureauRepository.findById(idBureau)
			.orElseThrow(() -> new ResourceNotFoundException("buraeu not found for this id :: " + idBureau));
	
	bureau.setNomBureau(bureauDetails.getNomBureau());
	bureau.setEmployes(bureauDetails.getEmployes());
	
	final Bureau updatedBureau = bureauRepository.save(bureau);
	return ResponseEntity.ok(updatedBureau);
}

@DeleteMapping("/{idBureau}")
public ResponseEntity deleteBureau(@PathVariable(name = "idBureau") Long idBureau) {
    if (idBureau == null) {
        return ResponseEntity.badRequest().body("Cannot remove Bureau with null ID");
    }
    Bureau bureau = bureauRepository.getOne(idBureau);
    if (bureau == null) {
        return  ResponseEntity.notFound().build();
    }
    bureauRepository.delete(bureau);
    return ResponseEntity.ok(bureauRepository.findAll());
}
}
