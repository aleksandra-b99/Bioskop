package com.example.Bioskop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Bioskop.entity.*;

public interface FilmRepository extends JpaRepository<Film, Long>{
	Film findByNazivIgnoreCase(String naziv);
	List<Film> findByZanrIgnoreCase(String zanr);
	List<Film> findByOcena(Double ocena);
	Film findByNazivAndZanrAndOcena(String naziv, String zanr, Double ocena);
	Film findByNazivAndZanr(String naziv, String zanr);
	List<Film> findAllByOrderByNaziv();
	List<Film> findAllByOrderByZanr();
	List<Film> findAllByOrderByOcenaAsc();
	Film findByNazivOrZanr(String naziv, String zanr);
}
