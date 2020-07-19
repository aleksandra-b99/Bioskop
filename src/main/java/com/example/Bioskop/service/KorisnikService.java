package com.example.Bioskop.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Bioskop.entity.Film;
import com.example.Bioskop.entity.Korisnik;
import com.example.Bioskop.entity.Menadzer;
import com.example.Bioskop.repository.KorisnikRepository;


@Service
public class KorisnikService implements UserDetailsService{
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	public Korisnik findByKorisnickoIme(String ime) {
        Korisnik korisnikk = this.korisnikRepository.findByKorisnickoIme( ime);
        return korisnikk;
    }
	public List<Korisnik> findAll() {
        List<Korisnik> korisnici = this.korisnikRepository.findAll();
        return korisnici;
    }
	public Korisnik save(Korisnik korisnik) {
		return this.korisnikRepository.save(korisnik);
	}
	public void delete(Long id) {
    	korisnikRepository.deleteById(id);
    }
	public List<Korisnik> findAllMenadzeri(String uloga) {
        List<Korisnik> menadzeri = this.korisnikRepository.findAllByUloga(uloga);
        return menadzeri;
    }
	public Korisnik findOne(Long id) {
		Korisnik korisnik=korisnikRepository.getOne(id);
		return korisnik;
	}
	//logovanje korisnika
	public UserDetails loadUserByUsername(String username)  {
		Korisnik korisnik =korisnikRepository.findByKorisnickoIme(username);
		if(korisnik==null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		}
		if(!korisnik.isAktivan()) {
			throw new UsernameNotFoundException(String.format("User nije aktivan '%s'.", username));
		}
		return new User(korisnik.getKorisnickoIme(),korisnik.getLozinka(),Arrays.asList(new SimpleGrantedAuthority("ROLE_" + korisnik.getUloga())));
	}
}
