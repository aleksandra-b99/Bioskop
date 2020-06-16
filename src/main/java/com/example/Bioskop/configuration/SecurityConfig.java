/*package com.example.Bioskop.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired 
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(userDetailsService)
		.passwordEncoder(encoder());
	}
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/add-bioskop","/save-bioskop","/delete/**","/delete-korisnik/**","/aktiviraj-korisnika/**","/deaktiviraj-korisnika/**")
		.hasRole("administrator")
		.antMatchers("/korisnici")
		.hasRole("menadzer")
		.antMatchers("/profil/**", "/svi_filmovi")
		.authenticated()
		.antMatchers("/","/**","/h2/**")
		.permitAll()
		
		.and()
		.formLogin()
		.loginPage("/loginuj-se")
		.loginProcessingUrl("/login")
		.usernameParameter("korisnickoIme")
		.passwordParameter("lozinka");
	}
}*/
