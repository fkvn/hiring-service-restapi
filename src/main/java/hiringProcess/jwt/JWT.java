package hiringProcess.jwt;


import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import hiringProcess.exception.JWTverifyTokenException;
import hiringProcess.model.core.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWT {

	@Value("${jwt.token.secretKey}")
	private String jwtKey1;

	private static final long JWT_TOKEN_VALIDITY = 7 * 24 * 60 * 60 * 1000; // 1 week

	public String generateJWT(Map<String,Object> claims) {
		@SuppressWarnings("deprecation")
		String jws = Jwts.builder()
		.setHeaderParam("typ","JWT")
		.setClaims(claims)
		.setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
		.signWith(SignatureAlgorithm.HS256, this.getJwtKey())
		.compact();
		return jws;
	}

	public String getJwtKey() {
		return Base64.getEncoder().encodeToString(this.jwtKey1.getBytes());
	}
	
	public void validateJWT(String jwt) {
		try { 			
			Jwts.parser().setSigningKey(this.getJwtKey()).parseClaimsJws(jwt); 
		} catch (JwtException ex) {     
			throw new JWTverifyTokenException(ex.getMessage());
		}
	}

	public Jws<Claims> getClaims(String jwt) {

		Jws<Claims> jwtClaim;
		try {
			jwtClaim = Jwts.parser()         
					.setSigningKey(this.getJwtKey())
					.parseClaimsJws(jwt);
			
			return jwtClaim;

		} catch (JwtException ex) {     
			throw new JWTverifyTokenException(ex.getMessage());
		}
	}

	public User getCurrentUser(Jws<Claims> jwtClaim) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		
		List<User> users = mapper.convertValue(jwtClaim.getBody().get("user"), new TypeReference<List<User>>() { });
		return users.get(0);
	}

}
