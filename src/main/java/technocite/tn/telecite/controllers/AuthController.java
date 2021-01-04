package technocite.tn.telecite.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import technocite.tn.telecite.entities.Employe;
import technocite.tn.telecite.jwt.JwtProvider;
import technocite.tn.telecite.services.EmployeService;
import technocite.tn.telecite.services.UserService;

import javax.validation.Valid;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController

public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private EmployeService employerepository;
    @PostMapping("drh/register")
    public String registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        Employe e = new Employe();
        e.setPassword(registrationRequest.getPassword());
        e.setEmail(registrationRequest.getEmail());
        e.setActive(registrationRequest.getActive());
        e.setDateEmbauche(registrationRequest.getDateEmbauche());
        e.setDateNaissance(registrationRequest.getDateNaissance());
        e.setIsChecked(registrationRequest.getActive());
        e.setMatricule(registrationRequest.getMatricule());
        e.setNomEmploye(registrationRequest.getNomEmploye());
        e.setPrenomEmploye(registrationRequest.getPrenomEmploye());
        e.setPhoto(registrationRequest.getPhoto());
        e.setPost(registrationRequest.getPost());
    	e.setEquipe(registrationRequest.getEquipe());
		e.setRole(registrationRequest.getRoleMember());
        userService.saveUser(e);
        return "OK";
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        Employe e = employerepository.findByEmailAndPassword(request.getEmail(), request.getPassword());
        String token = jwtProvider.generateToken(e.getEmail());
        return new AuthResponse(token);
    }
}
