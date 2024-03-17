package com.binary.configuration;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private static String SECRET_KEY = "DFSDFG255S1FG5SD1FGSDFGB5G5D12323DSFGS5D6GS2D3G21DSF2GSDRERTDS33S66121GADSGS6323ASDFGDFFAEE";
	private long jwtExpiration = 86400000; // 1 day

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims extractedClaims = extractAllClaims(token);
		return claimsResolver.apply(extractedClaims);
	}

	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap(), userDetails);
	}

	public String generateToken(Map<String, Object> extractClaims, UserDetails userDetails) {
		return buildToken(extractClaims, userDetails, jwtExpiration);
	}

	public String buildToken(Map<String, Object> extractClaims, UserDetails userDetails, long expiration) {
		return Jwts.builder().setClaims(extractClaims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}

	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}

	public boolean isTokenExpired(String token) {
		Date expiratioDate = extractExpiration(token);
		return (expiratioDate != null) ? expiratioDate.before(new Date()) : false;
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}

	public Key getSignKey() {
		byte[] key = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(key);
	}

}
