package com.santanderdio.springsecurity.security;

import java.util.List;
import java.util.stream.Collectors;

import com.santanderdio.springsecurity.enums.Enumeradores.Role;

import edu.emory.mathcs.backport.java.util.Arrays;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JWTCreator {
	
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String ROLES_AUTHORITIES = "authorities";

    public static String create(String prefix, String key, JWTObject jwtObject) {
        String token = Jwts.builder()
        		.setSubject(jwtObject.getSubject())
        		.setIssuedAt(jwtObject.getIssueAt())
        		.setExpiration(jwtObject.getExpirationAt())
                .claim(ROLES_AUTHORITIES, checkRoles(jwtObject.getRoles()))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        return prefix + " " + token;
    }
    
    public static JWTObject create(String token, String prefix, String key)
            throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException {
        JWTObject object = new JWTObject();
        
        token = token.replace(prefix, "");
        
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        
        object.setSubject(claims.getSubject());
        object.setExpirationAt(claims.getExpiration());
        object.setIssueAt(claims.getIssuedAt());
        object.setRoles(claims.get(ROLES_AUTHORITIES).toString().replaceAll("\\[|\\]", ""));
//        object.setRoles((List)claims.get(ROLES_AUTHORITIES));
        return object;

    }
    
  private static List<String> checkRoles(List<String> roles) {
      return roles.stream().map(s -> "ROLE_".concat(s.replaceAll("ROLE_",""))).collect(Collectors.toList());
  }

    
//  private static List<String> checkRoles(List<Role> roles) {
//      List<String> rls = roles.stream().map(s -> {
//    	  System.out.println(s.name());
//    	  return s.name();
//      }).collect(Collectors.toList());
//      return rls;
////      return roles.stream().map(s -> s.name().substring(7)).collect(Collectors.toList());
//  }


}