package technocite.tn.telecite.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import technocite.tn.telecite.jwt.JwtFilter;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
@Autowired
private JwtFilter jwtFilter;
@Bean
public PasswordEncoder passwordEncoder() {
	
	return new BCryptPasswordEncoder();
}

@Override
protected void configure(HttpSecurity http) throws Exception {
	http.httpBasic().disable()
	.csrf().disable()
	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	.and()
	.authorizeRequests()
	.antMatchers("/drh/*").hasRole("DRH")
	.antMatchers("/scrummaster/*").hasRole("SCRUM_MASTER")
	.antMatchers("/productowner/*").hasRole("PRODUCT_OWNER")
	.antMatchers("/employe/*").hasRole("EMPLOYE")
	.antMatchers("/scrumteammember/*").hasRole("SCRUM_TEAM_MEMBER")
	.antMatchers("/devteammember/*").hasRole("DEV_TEAM_MEMBER")
	.antMatchers("/register","/auth").permitAll()
	.and()
	.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class );

}
}
