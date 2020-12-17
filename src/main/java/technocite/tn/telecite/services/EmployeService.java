package technocite.tn.telecite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import technocite.tn.telecite.entities.Employe;
import technocite.tn.telecite.entities.RoleMember;
import technocite.tn.telecite.repositories.IEmploye;
import technocite.tn.telecite.repositories.IRoleMmember;
@Service
public class EmployeService {

	
//@Autowired
//private IRoleMmember roleRepository;
@Autowired	
private PasswordEncoder paswordEncoder; 
@Autowired	
private IEmploye employerepository; 

public Employe saveEmploye (Employe employe) {
    System.out.println("gggggggggggggggggggggggggg"+employe);
//    RoleMember roleMember = roleRepository.findByNomRole(employe.getRoleMember().getNomRole());
//    System.out.println("gggggggggggggggggggggggggg"+employe.getRoleMember().getNomRole());
//
//    employe.setRoleMember(roleMember);
//	
    employe.setPassword(paswordEncoder.encode(employe.getPassword()));
    return employerepository.save(employe);

}

public Employe findByEmail(String email) {
	
	return employerepository.findByEmail(email);
	
}
public Employe findByEmailAndPassword(String email, String password) {
	Employe employe =findByEmail(email);
	if(employe!=null) {
	if(paswordEncoder.matches(password,employe.getPassword())) {
		return employe;
	}
	}
	return null;	
}
}

