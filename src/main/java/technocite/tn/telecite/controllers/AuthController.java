package technocite.tn.telecite.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import technocite.tn.telecite.entities.Employe;
import technocite.tn.telecite.jwt.JwtProvider;
import technocite.tn.telecite.services.EmployeService;

@RestController
public class AuthController {
	  @Autowired
	    private EmployeService employeService;
	    @Autowired
	    private JwtProvider jwtProvider;

	    @PostMapping("/register")
	    public String registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
	        Employe employe = new Employe();

			employe.setMatricule(registrationRequest.getMatricule());
			employe.setNomEmploye(registrationRequest.getNomEmploye());
			employe.setPrenomEmploye(registrationRequest.getPrenomEmploye());
			employe.setDateNaissance(registrationRequest.getDateNaissance());
			employe.setEmail(registrationRequest.getEmail());
			employe.setPassword(registrationRequest.getPassword());
			employe.setDateEmbauche(registrationRequest.getDateEmbauche());
			employe.setSalaire(registrationRequest.getSalaire());
			
			employe.setPost(registrationRequest.getPost());
			employe.setRole(registrationRequest.getRole());
			employe.setActive(registrationRequest.getActive());
			employe.setPhoto(registrationRequest.getPhoto());
			employe.setBureau(registrationRequest.getBureau());
			employe.setDepartement(registrationRequest.getDepartement());
			employe.setEquipe(registrationRequest.getEquipe());
			employe.setRoleMember(registrationRequest.getRoleMember());
	        System.out.println("fffffffffffffffffffffffffffffffffffffffffffffffff"+employe.getEmail());
	        employeService.saveEmploye(employe);
	        return "OK";
	    }

	    @PostMapping("/auth")
	    public AuthResponse auth(@RequestBody AuthRequest request) {
	        Employe employe = employeService.findByEmailAndPassword(request.getEmail(), request.getPassword());
	        System.out.println("gggggggggggggggggggggggggggggggg"+employe);

	        String token = jwtProvider.generateToken(employe.getEmail());
	        System.out.println("gggggggggggggggggggggggggggggggg"+token);
	        return new AuthResponse(token );
	    }
	}