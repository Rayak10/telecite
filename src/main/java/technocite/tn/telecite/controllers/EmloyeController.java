package technocite.tn.telecite.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import technocite.tn.telecite.entities.Employe;
import technocite.tn.telecite.entities.Projet;
import technocite.tn.telecite.exception.ResourceNotFoundException;
import technocite.tn.telecite.repositories.IEmploye;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/telecite/employes")
public class EmloyeController {
	
	
	@Autowired
	private IEmploye employeRepository;
	
	
	@GetMapping("/")
	public ResponseEntity findAll() {
		
		return  ResponseEntity.ok(employeRepository.findAll());
	}
	
	@GetMapping("/AllActives/{isActive}")
	public ResponseEntity findEmployesActivesOrNot(@PathVariable(name = "isActive") boolean isActive) {
		if (StringUtils.isEmpty(isActive) ) {
            return ResponseEntity.badRequest().body("Cannot find with empty employe active");
        }
		List<Employe> employes = employeRepository.findByActive(isActive);
		return  ResponseEntity.ok(employes);
	}

	@GetMapping("/{idEmploye}")
	
	public ResponseEntity findEmployeById(@PathVariable(name="idEmploye") Long idEmploye) { 
		
		  if (idEmploye == null) {
	            return ResponseEntity.badRequest().body("Cannot retrieve Employe with null ID");
	        }
	        Optional<Employe> employe = employeRepository.findById(idEmploye);
	        if (employe == null) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok().body(employe);
	}
	@GetMapping("/nom/{nomEmploye}")

	public ResponseEntity findByNomEmploye(@PathVariable(name="nomEmploye") String nomEmploye) { 
		 if (nomEmploye == null) {
	            return ResponseEntity.badRequest().body("Cannot retrieve employe with null name");
	        }
		  Employe employe = employeRepository.findByNomEmploye(nomEmploye);
	        if (employe == null) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok().body(employe);
	}
	
	
	@GetMapping("/nomPrenom/{nomEmploye}/{prenomEmploye}")

	public ResponseEntity findByNomEmployeAndPrenomEmploye(@PathVariable(name="nomEmploye") String nomEmploye,@PathVariable(name="prenomEmploye") String prenomEmploye) { 
		 if (nomEmploye == null) {
	            return ResponseEntity.badRequest().body("Cannot retrieve employe with null name");
	        }
		  Employe employe = employeRepository.findByNomEmployeAndPrenomEmploye(nomEmploye, prenomEmploye);
	        if (employe == null) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok().body(employe);
	}
	@PostMapping("/")
    	public ResponseEntity createEmploye(@RequestBody Employe employe) {
        if (employe == null) {
            return ResponseEntity.badRequest().body("Cannot create employe with empty fields");
        }
        Employe createEmploye = employeRepository.save(employe);
        return ResponseEntity.ok(createEmploye);
    }
	 @PutMapping("/update/{idEmploye}")
		public ResponseEntity<Employe> updateEmploye(@PathVariable(value = "idEmploye") Long idEmploye, @RequestBody 
			Employe employeeDetails) throws ResourceNotFoundException {
			Employe employe = employeRepository.findById(idEmploye)
					.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + idEmploye));
			
			employe.setMatricule(employeeDetails.getMatricule());
			employe.setNomEmploye(employeeDetails.getNomEmploye());
			employe.setPrenomEmploye(employeeDetails.getPrenomEmploye());
			employe.setDateNaissance(employeeDetails.getDateNaissance());
			employe.setEmail(employeeDetails.getEmail());
			employe.setPassword(employeeDetails.getPassword());
			employe.setDateEmbauche(employeeDetails.getDateEmbauche());
			employe.setSalaire(employeeDetails.getSalaire());
			
			employe.setPost(employeeDetails.getPost());
			employe.setRole(employeeDetails.getRole());
			employe.setActive(employeeDetails.isActive());
			employe.setPhoto(employeeDetails.getPhoto());
			
			final Employe updatedEmploye = employeRepository.save(employe);
			return ResponseEntity.ok(updatedEmploye);
		}
	@PostMapping("/login")
    public ResponseEntity login(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password) {
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            return ResponseEntity.badRequest().body("Cannot login with empty employe email or password");
        }
        Employe authenticatedEmploye = employeRepository.findByEmailAndPassword(email, password);
        if (authenticatedEmploye == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(authenticatedEmploye);
    }
	
	 @DeleteMapping("/{idEmploye}")
	    public ResponseEntity deleteEmploye(@PathVariable(name = "idEmploye") Long idEmploye) {
	        if (idEmploye == null) {
	            return ResponseEntity.badRequest().body("Cannot remove employe with null ID");
	        }
	        Employe employe = employeRepository.getOne(idEmploye);
	        if (employe == null) {
	            return  ResponseEntity.notFound().build();
	        }
	        employeRepository.delete(employe);
	        return ResponseEntity.ok(employeRepository.findAll());
	    }
	 @GetMapping("/active/{idEmploye}/{isActive}")
	    public ResponseEntity employeActif(@PathVariable(name = "idEmploye") Long idEmploye, @PathVariable(name = "isActive") boolean isActive) {
	        if (idEmploye == null) {
	            return ResponseEntity.badRequest().body("Cannot see employe with null ID");
	        }
	        Employe employe = employeRepository.getOne(idEmploye);
	        if (employe == null) {
	            return  ResponseEntity.notFound().build();
	        }
	        employe.setActive(isActive);
	        return ResponseEntity.ok(employeRepository.save(employe));
	    }
	

	
}