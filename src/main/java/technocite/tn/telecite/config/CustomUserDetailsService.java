package technocite.tn.telecite.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import technocite.tn.telecite.entities.Employe;
import technocite.tn.telecite.repositories.IEmploye;
import technocite.tn.telecite.services.UserService;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private IEmploye userService;

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("2222222222222222222222"+email);

        Employe emp = userService.findByEmail(email);
        System.out.println("2222222222222222222222"+emp.getEmail());

        return CustomUserDetails.fromUserEntityToCustomUserDetails(emp);
    }
}

