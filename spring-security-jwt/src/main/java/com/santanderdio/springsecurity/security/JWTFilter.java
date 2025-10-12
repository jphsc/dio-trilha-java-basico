package com.santanderdio.springsecurity.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.santanderdio.springsecurity.enums.Enumeradores.Role;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/* todas as requisicoes serao capturadas por essa filter 
 * 
 * */

public class JWTFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //obtem o token da request com AUTHORIZATION
        String token =  request.getHeader(JWTCreator.HEADER_AUTHORIZATION);
        
        //esta implementação só esta validando a integridade do token
        try {
        	
            if(token != null && !token.isEmpty()) {
                
                JWTObject tokenObject = JWTCreator.create(token, SecurityConfig.PREFIX, SecurityConfig.KEY);

                List<SimpleGrantedAuthority> authorities = authorities(tokenObject.getRoles());
                
                UsernamePasswordAuthenticationToken userToken =
                        new UsernamePasswordAuthenticationToken(
                                tokenObject.getSubject(),
                                null,
                                authorities);

                SecurityContextHolder.getContext().setAuthentication(userToken);

            } else {
                SecurityContextHolder.clearContext();
            }
            
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException e) {
        	e.printStackTrace();
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return;
        }
    }
    
    private List<SimpleGrantedAuthority> authorities(List<String> roles){
        return roles.stream()
//        		.map(SimpleGrantedAuthority::new) // código antigo, antes do erro de ROLE_
        		.map(role -> {
                    // Remove "ROLE_" do início se presente
                    String processedRole = role;
                    if (processedRole.startsWith("ROLE_")) {
                        processedRole = processedRole.substring(5);
                    }
                    // Remove colchetes [ ]
                    processedRole = processedRole.replace("[", "").replace("]", "");
                    // Converte para o formato que o Spring Security espera
                    return new SimpleGrantedAuthority("ROLE_" + processedRole);
        		})
                .collect(Collectors.toList());
    }
}