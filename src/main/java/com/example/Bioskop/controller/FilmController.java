package com.example.Bioskop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.Bioskop.entity.Film;
import com.example.Bioskop.service.FilmService;


@Controller
public class FilmController {
	@Autowired
	private FilmService filmService;
	
	@GetMapping("/filmovi")
	public String getFilmovi(Model model) {
		List<Film> filmList=this.filmService.findAll();
		model.addAttribute("filmovi", filmList);
		return "filmovi.html";
	}
	@GetMapping("/filmovi/{id}")
    public String getEmployee(@PathVariable(name = "id") Long id, Model model) {
    	Film film = this.filmService.findOne(id);
    	model.addAttribute("film", film);
    	return "film.html";
    }
}
