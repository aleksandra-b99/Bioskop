package com.example.Bioskop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Bioskop.entity.*;

public interface FilmRepository extends JpaRepository<Film, Long>{
	Film findByNazivIgnoreCase(String naziv);
	List<Film> findByZanrIgnoreCase(String zanr);
}
