package technocite.tn.telecite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import technocite.tn.telecite.entities.Employe;
import technocite.tn.telecite.entities.RoleMember;
import technocite.tn.telecite.repositories.IEmploye;
import technocite.tn.telecite.repositories.IRoleMmember;

@Service
public class UserService {

    @Autowired
    private IRoleMmember roleMmemberrepository;
    @Autowired
    private IEmploye employerepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Employe saveUser(Employe emp) {
        RoleMember roleMember = roleMmemberrepository.findByNomRole(emp.getRole().getNomRole());
        emp.setRole(roleMember);
        emp.setPassword(passwordEncoder.encode(emp.getPassword()));
        
        return employerepository.save(emp);
    }

    public Employe findByEmail(String email) {
        System.out.println("55555555555555555555555"+ email);

        return employerepository.findByEmail(email);
    }

    public Employe findByLoginAndPassword(String password ,String email ) {
    	Employe employe = findByEmail(email);
        System.out.println("55555555555555555555555"+ employe);

        if (employe != null) {
            if (passwordEncoder.matches(password, employe.getPassword())) {
                return employe;
            }
        }
        return null;
    }
}
