package com.cbnits.CBNITS_TRADE.JwtUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
//import org.hibernate.annotations.common.util.impl.Log.logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.fasterxml.jackson.core.JsonParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;


@Service
@Component
public class JwtUtil {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

	 private String SECRET_KEY = "secret";

	    public String extractUsername(String token) {
	        return extractClaim(token, Claims::getSubject);
	    }

	    public Date extractExpiration(String token) {
	        return extractClaim(token, Claims::getExpiration);
	    }

	    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	        final Claims claims = extractAllClaims(token);
	        return claimsResolver.apply(claims);
	    }
	    private Claims extractAllClaims(String token) {
	        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	    	
	    }

	    private Boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	    }

	    public String generateToken(UserDetails userDetails) {
	        Map<String, Object> claims = new HashMap<>();
	        return createToken(claims, userDetails.getUsername());
	    }

	    private String createToken(Map<String, Object> claims, String subject) {

	        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
	                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	    }

	    

	    
	    public Boolean validateToken(String token, UserDetails userDetails)   {
//	       
//	    	if(r.hasErrors()){
//	    		throw new JsonParseException(null, token);
//	    	}
	    	
	    	
//	    	 try  {
	    	final String username = extractUsername(token);
	        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//	        }catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
//				throw new MalformedJwtException("INVALID_CREDENTIALS");
//			} catch (ExpiredJwtException ex) {
//				throw ex;
//			}
        
	        
	        
	        
//	        catch (SignatureException e) {
//				logger.error("Invalid JWT signature: {}", e.getMessage());
//			}
//	        catch (JsonParseException e) {
////				logger.error("Invalid JWT token: {}", e.getMessage());
//	        		throw new JsonParseException(null, token);
	        	
//			} 
//	        catch (ExpiredJwtException e) {
//				logger.error("JWT token is expired: {}", e.getMessage());
//			} 
//	        catch (UnsupportedJwtException e) {
//				logger.error("JWT token is unsupported: {}", e.getMessage());
//			} 
//	        catch (IllegalArgumentException e) {
//				logger.error("JWT claims string is empty: {}", e.getMessage());
//			}
//			return false;
		}
	    }

