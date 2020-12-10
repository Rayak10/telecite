package technocite.tn.telecite.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.UserDetailsService;

import technocite.tn.telecite.entities.Employe;
import technocite.tn.telecite.services.EmployeService;

@Component
public class CustomUserDetailsService implements UserDetailsService {
	 @Autowired
	    private EmployeService employeservice;

	    @Override
	    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        Employe employe = employeservice.findByEmail(username);
	        return CustomUserDetails.fromUserEntityToCustomUserDetails(employe);
	    }
	}

