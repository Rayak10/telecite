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
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/ROLE_ADMIN/*").hasRole("ADMIN")
                .antMatchers("/ROLE_USER/*").hasRole("USER")
                .antMatchers("*/ROLE_DRH/*").hasRole("DRH")
                .antMatchers("/ROLE_SCRUM_MASTER/*").hasRole("SCRUM_MASTER")
                .antMatchers("/ROLE_EMPLOYE/*").hasRole("EMPLOYE")
                .antMatchers("/ROLE_PRODUCT_OWNER/*").hasRole("PRODUCT_OWNER")
                .antMatchers("/ROLE_DEV_TEAM_MEMBER/*").hasRole("DEV_TEAM_MEMBER")
                .antMatchers("/ROLE_SCRUM_TEAM_MEMBER/*").hasRole("SCRUM_TEAM_MEMBER")
                .antMatchers("/register", "telecite/employes/auth").permitAll()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        System.out.println("1000000000000000000000");

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
