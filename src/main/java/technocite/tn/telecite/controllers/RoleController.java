package technocite.tn.telecite.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import technocite.tn.telecite.entities.RoleMember;
import technocite.tn.telecite.repositories.IEmploye;
import technocite.tn.telecite.repositories.IRoleMmember;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/telecite/roles")
public class RoleController {
	@Autowired
	private IRoleMmember roleRepository;
	@Autowired
	private IEmploye employeRepository;
	
	
	
@GetMapping("/")
	public ResponseEntity findAll() {
		
		return  ResponseEntity.ok(roleRepository.findAll());
	}
@GetMapping("/{idRole}")
	
	public ResponseEntity findRoleMemberById(@PathVariable(name="idRole") Long idRole) { 
		
		  if (idRole == null) {
	            return ResponseEntity.badRequest().body("Cannot retrieve role with null ID");
	        }
	        Optional<RoleMember> roleMember = roleRepository.findById(idRole);
	        if (roleMember == null) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok().body(roleMember);
	}

}
