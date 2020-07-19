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
	public List<Film> findByOcena(Double ocena){
		 List<Film> filmList=this.filmRepository.findByOcena(ocena);
		 return filmList;
	}
	public Film findByNazivAndZanrAndOcena(String naziv, String zanr, Double ocena) {
		return filmRepository.findByNazivAndZanrAndOcena(naziv, zanr, ocena);
	}
	public Film findByNazivAndZanr(String naziv, String zanr) {
		return filmRepository.findByNazivAndZanr(naziv, zanr);
	}
	public List<Film> sortNaziv() {
		return filmRepository.findAllByOrderByNaziv();
	}
	public List<Film> sortZanr() {
		return filmRepository.findAllByOrderByZanr();
	}
	public List<Film> sortOcena() {
		return filmRepository.findAllByOrderByOcenaAsc();
	}
	public Film findByNazivOrZanr(String naziv, String zanr) {
		return filmRepository.findByNazivOrZanr(naziv, zanr);
	}
}
	
