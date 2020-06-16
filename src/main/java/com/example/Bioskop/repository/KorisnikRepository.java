package com.example.Bioskop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Bioskop.entity.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long>{
	Korisnik findByKorisnickoImeAndLozinka(String ime, String lozinka);
	List<Korisnik> findAllByUloga(String uloga);
	Korisnik findByKorisnickoIme(String ime);
}
