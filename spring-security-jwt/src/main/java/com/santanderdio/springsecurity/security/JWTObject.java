package com.santanderdio.springsecurity.security;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class JWTObject {
	
	private String subject; // nome do usuario
	private Date issueAt; // data de criacao do token
	private Date expirationAt; // data de expiração do token
	private List<String> roles; // perfis de acesso
//	private List<Role> roles; // perfis de acesso
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public Date getIssueAt() {
		return issueAt;
	}
	
	public void setIssueAt(Date issueAt) {
		this.issueAt = issueAt;
	}
	
	public Date getExpirationAt() {
		return expirationAt;
	}
	
	public void setExpirationAt(Date expirationAt) {
		this.expirationAt = expirationAt;
	}
	
	public List<String> getRoles() {
		return roles;
	}
	
	public void setRoles(String ...roles) {
		this.roles = Arrays.asList(roles);
	}
}
