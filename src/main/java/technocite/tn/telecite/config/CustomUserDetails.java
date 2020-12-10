package technocite.tn.telecite.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import technocite.tn.telecite.entities.Employe;


public class CustomUserDetails implements UserDetails{
	  private String login;
	    private String password;
	    private Collection<? extends GrantedAuthority> grantedAuthorities;

	    public static CustomUserDetails fromUserEntityToCustomUserDetails(Employe employe) {
	        CustomUserDetails c = new CustomUserDetails();
	        c.login = employe.getEmail();
	        c.password = employe.getPassword();
	        c.grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(employe.getRoleMember().getNomRole()));
	        return c;
	    }

	    @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        return grantedAuthorities;
	    }

	    @Override
	    public String getPassword() {
	        return password;
	    }

	    @Override
	    public String getUsername() {
	        return login;
	    }

	    @Override
	    public boolean isAccountNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	        return true;
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isEnabled() {
	        return true;
	    }
	}

 
