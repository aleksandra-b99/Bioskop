package com.example.Bioskop.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Qualifier("korisnikService")
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
				.antMatchers("/login.html","/registar.html", "/h2/**","/terminskaLista.html","api/korisnici/**","/filmovi.html","/").permitAll()
				.antMatchers("profil.html").authenticated()//samo onima koji su ulogovani
				.antMatchers("/oceni-film.html").hasAnyRole("gledaoc")
				.antMatchers("/sale.html","/bioskopi.html","/izmenaSale.html","/dodaj-salu.html","/kreiraj-TerminskuListu.html","/izmeni-TerminskuListu").hasAnyRole("menadzer")
				.antMatchers("/korisnici.html","/svi-bioskopi.html","/add-bioskop.html","/izmeneBioskopa").hasAnyRole("admin")
				//.antMatchers("/korisnici.html")
				.and()
				.csrf().disable()
				.headers().frameOptions().disable()
				.and()
				.exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint())
				.and()
				.formLogin()
				.and()
				.logout()
				.logoutSuccessHandler((new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK)))
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID");

	}

	@Bean
	public AuthenticationEntryPoint unauthorizedEntryPoint() {
		return (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
	}

}