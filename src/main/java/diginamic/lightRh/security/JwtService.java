package diginamic.lightRh.security;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import diginamic.lightRh.exceptions.treatmentExceptions.InactiveEmployeeException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String SECRET_KEY = "1C2960F32C0E744CF233BF373CF82A520F672B1CAA279DDC6BADB7EFE80A3AED";
    
    public String extractUserEmail(String token) {
	return extractClaims(token, Claims::getSubject);
    }
    
    public <T> T extractClaims(String token, Function<Claims, T> claimsExtractor) {
	final Claims claims = extractAllClaims(token);
	return claimsExtractor.apply(claims);
    }
    
    public String generateToken(UserDetails userDetails) throws InactiveEmployeeException {
	
	if(userDetails.isEnabled())
		return Jwts
		.builder()
		.setSubject(userDetails.getUsername())
		.setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis()+ (1000 * 60 * 60 * 5)))
		.signWith(getSignInKey(), SignatureAlgorithm.HS256)
		.compact();
	else throw new InactiveEmployeeException("This employee is disabled and can't acces application");
    }
    
    public String generateToken(UserDetails usertDetails, Map<String, Object> extraClaims ) {
	return Jwts
		.builder()
		.setClaims(extraClaims)
		.setSubject(usertDetails.getUsername())
		.setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 10))
		.signWith(getSignInKey(), SignatureAlgorithm.HS256)
		.compact();
    }
    
    public boolean isTokenValid(String token, UserDetails userDetails) {
	final String userEmail = extractUserEmail(token);
	return (userEmail.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    
    private boolean isTokenExpired(String token) {
	return extractExpiration(token).before(new Date(System.currentTimeMillis()));
    }

    private Date extractExpiration(String token) {
	return extractClaims(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
	return Jwts
		.parserBuilder()
		.setSigningKey(getSignInKey())
		.build()
		.parseClaimsJws(token)
		.getBody();
    }

    private Key getSignInKey() {
	byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
	return Keys.hmacShaKeyFor(keyBytes);
    }
}
