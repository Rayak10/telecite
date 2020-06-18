package technocite.tn.telecite.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import technocite.tn.telecite.entities.Employe;
import technocite.tn.telecite.repositories.IEmploye;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/telecite/emlpoyes")
public class EmloyeController {
	
	
	@Autowired
	private IEmploye employeRepository;
	
	
	@GetMapping("/")
	public ResponseEntity findAll() {
		
		return  ResponseEntity.ok(employeRepository.findAll());
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
	
	@PostMapping("/")
    public ResponseEntity createEmploye(@RequestBody Employe employe) {
        if (employe == null) {
            return ResponseEntity.badRequest().body("Cannot create employe with empty fields");
        }
        Employe createEmploye = employeRepository.save(employe);
        return ResponseEntity.ok(createEmploye);
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
	        return ResponseEntity.ok("employe removed with success");
	    }

	
}
