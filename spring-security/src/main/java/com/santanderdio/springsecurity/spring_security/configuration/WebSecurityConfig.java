package com.santanderdio.springsecurity.spring_security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import com.santanderdio.springsecurity.spring_security.exception.CustomAccessDeniedException;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
//	@Bean
//	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		 
//		 http
//         .csrf(csrf -> csrf.disable()) // Desabilita o CSRF
////		 .csrf(csrf -> csrf.ignoringRequestMatchers("/produto"))
//         .authorizeHttpRequests(
//        		 authorize -> authorize
//        		 .anyRequest().authenticated() // Exige autenticação para todas as requisições
//         )
////         .formLogin(Customizer.withDefaults())
//         .exceptionHandling(exception -> exception //interceptador de exceções do spring security
//        		 .accessDeniedHandler((request, response, authException) -> 
//        		 	{response.sendError(HttpServletResponse.SC_FORBIDDEN, "Autenticação necessária");
//        		 	System.out.println("ERRO GERADO: "+authException.getMessage());
//        		 	})
//        		 .authenticationEntryPoint((request, response, authException) -> 
//        		 	{
//        		 		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied");
//        		 		System.out.println("ERRO GERADO: "+authException.getMessage());
//        		 	})
//         )
//         .httpBasic(Customizer.withDefaults()) // Habilita a autenticação básica (HTTP Basic) >> versao nova
//         .sessionManagement(
//        		 session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Não cria sessões
//         ;
//
//	     return http.build();
//	 }
//	
//	@Bean
//	UserDetailsService userDetailsService() {
//	     // Configura um usuário em memória
//	     UserDetails admin = User.withUsername("admin") // Basic YWRtaW46MTIzNDU2
//	         .password("{noop}123456") // {noop} indica que a senha não está codificada
////	         .password(passwordEncoder().encode("123456"))
//	         .roles("ADMIN" )
//	         .build();
//	     
//	     UserDetails user = User.withUsername("usuario") // Basic dXN1YXJpbzoxMjM0NTY=
//		     .password("{noop}123456") // {noop} indica que a senha não está codificada
////		     .password(passwordEncoder().encode("123456"))
//	         .roles("USER")
//	         .build();
//	
//	     return new InMemoryUserDetailsManager(admin, user); // Retorna um UserDetailsService com o usuário configurado
//	 }
//	 
//	 public PasswordEncoder passwordEncoder() {
//		 return new BCryptPasswordEncoder();
//	 }
	
	@Autowired 
	private SecurityDatabaseService securityService;

	@Autowired
	public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(securityService).passwordEncoder(NoOpPasswordEncoder.getInstance());
	}
	
	/*public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("ad1")
			.password("{noop}senha")
			.roles("users")
			.and()
			.withUser("admin")
			.password("{noop}senha")
			.roles("managers");
	}*/
		
	public void configure(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests()
			.antMatchers("/welcome").permitAll()
			.antMatchers("/**/user").hasAnyRole("USER", "ADMIN")
			.antMatchers("/**/admin").hasRole("ADMIN")
			.anyRequest().authenticated()
			.and().exceptionHandling(ex -> ex
					.accessDeniedHandler((request, response, accessDeniedException) -> {
						throw new CustomAccessDeniedException("Você não tem permissão para acessar esta página", accessDeniedException);
					}))
//			.formLogin(Customizer.withDefaults()); // autenticacao vai tela de login do spring
			.httpBasic(Customizer.withDefaults()); // autenticacao basica
	}

}
