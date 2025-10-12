package com.santanderdio.springsecurity.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.santanderdio.springsecurity.enums.Enumeradores.Role;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
@DynamicUpdate
public class User 
//implements UserDetails
{

//	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer id;
	
	@Column(name = "user_name", length = 50, nullable = false)
	private String name;

	@Column(name = "user_username", length = 20, nullable = false)
	private String username;
	
	@Column(name = "user_password", length = 100, nullable = false)
	private String password;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), foreignKey = @ForeignKey(name = "fk_user_userroles_01"))
	@Column(name = "role_id")
	private List<Role> roles = new ArrayList<>();


	public User() {
		
	}
	
	public User(Builder b) {
		this.id = b.id;
		this.name = b.name;
		this.username = b.username;
		this.password = b.password;

//		for(Role role: this.roles) {
//			b.roles.add(role);
//		}
		for(Role role: b.roles) {
			this.roles.add(role);
		}
	};
	
	public static class Builder {
		private Integer id;
		private String name;
		private String username;
		private String password;	
		private List<Role> roles;

		public User build() {
			return new User(this);
		}

		public Builder setId(Integer id) {
			this.id = id;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setUsername(String username) {
			this.username = username;
			return this;
		}

		public Builder setPassword(String password) {
			this.password = password;
			return this;
		}

		public Builder setRoles(List<Role> list) {
			this.roles = list;
			return this;
		};
		
	}
	
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return roles;
//	}
//
//	@Override
//	public String getPassword() {
//		return senha;
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return ativo;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return ativo;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return ativo;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return ativo;
//	}
}

