package com.example.Bioskop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Bioskop.entity.Gledaoc;

public interface GledaocRepository extends JpaRepository<Gledaoc, Long>{
	Gledaoc findByKorisnickoIme(String korisnickoIme);
	
}
