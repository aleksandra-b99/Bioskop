package com.example.Bioskop.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Bioskop.entity.Film;
import com.example.Bioskop.entity.Korisnik;
import com.example.Bioskop.entity.Menadzer;
import com.example.Bioskop.repository.KorisnikRepository;


@Service
public class KorisnikService {//implements UserDetailsService{
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	public Korisnik findByKorisnickoImeAndLozinka(String ime, String lozinka) {
        Korisnik korisnikk = this.korisnikRepository.findByKorisnickoImeAndLozinka(ime, lozinka);
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
	/*@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Korisnik korisnik =korisnikRepository.findByKorisnickoIme(username);
		if(korisnik==null) {
			throw new
		}
		return new User(korisnik.getKorisnickoIme(),korisnik.getLozinka(),Arrays.asList(new SimpleGrantedAuthority(korisnik.getUloga())));
	}*/
}
