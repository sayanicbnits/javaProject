package com.cbnits.CBNITS_TRADE.config;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.cbnits.CBNITS_TRADE.JwtUtils.JwtUtil;
import com.fasterxml.jackson.core.JsonParseException;

import io.jsonwebtoken.MalformedJwtException;

//import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component("restAuthenticationEntryPoint")
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

//    private static final Throwable MalformedJwtException = null;

	@Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws JsonParseException,IOException, ServletException, MalformedJwtException {
       System.out.println("!!!!!!!!!!!IT WORKS!!!!!!!!!!!!");
//       response.sendError(response.SC_UNAUTHORIZED,
//            "Sorry, You're not authorized to access this resource.");
       response.setContentType("application/json");
       response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
       response.getOutputStream().println("{ \"error\": \"" +  authenticationException.getMessage() + "\" }");
    }
//	          
	
}
