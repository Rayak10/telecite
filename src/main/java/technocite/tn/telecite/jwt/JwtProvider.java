package technocite.tn.telecite.jwt;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Slf4j

@Component
@Log
public class JwtProvider  {
	
@Value("$(jwt.secret)")
private String jwtSecret;

public String generateToken(String login) {
	Date date= Date.from(LocalDate.now().plusDays(15).atStartOfDay(ZoneId.systemDefault()).toInstant());
	return Jwts.builder()
			.setSubject(login)
			.setExpiration(date)
			.signWith(SignatureAlgorithm.HS512, jwtSecret)
			.compact();
	}

public boolean validateToken(String token) {
    try {
        Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
        return true;
    } catch (Exception e) {
        System.out.println("invalid token");
    }
    return false;
}

public String getLoginFromToken(String token) {
    Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    return claims.getSubject();
}
}
