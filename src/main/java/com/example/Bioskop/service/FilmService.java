package com.example.Bioskop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Bioskop.entity.Film;
import com.example.Bioskop.entity.Menadzer;
import com.example.Bioskop.repository.FilmRepository;

@Service
public class FilmService {
	@Autowired
	private FilmRepository filmRepository;
	
	public List<Film> findAll() {
        List<Film> filmovi = this.filmRepository.findAll();
        return filmovi;
    }
	public Film findOne(Long id) {
        Film film = this.filmRepository.getOne(id);
        return film;
    }
	public Film find_by_naziv(String naziv) {
		Film filmm=this.filmRepository.findByNazivIgnoreCase(naziv);
		return filmm;
	}
	public List<Film> find_by_zanr(String zanr) {
		List<Film> filmList=this.filmRepository.findByZanrIgnoreCase(zanr);
		return filmList;
	}
	
}
	
