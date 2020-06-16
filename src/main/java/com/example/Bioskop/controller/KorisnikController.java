package com.example.Bioskop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.Bioskop.entity.Film;
import com.example.Bioskop.entity.Korisnik;
import com.example.Bioskop.service.FilmService;
import com.example.Bioskop.service.KorisnikService;

@Controller
public class KorisnikController {
	@Autowired
	private KorisnikService korisnikService;
	@Autowired
	private FilmService filmService;
	
	@GetMapping("/")
    public String welcome() {
        return "pocetna.html";
    }
	@GetMapping("/loginuj-se")
    public String loginujSe() {
        return "login.html";
    }
	@PostMapping("/login")
	public String login(@ModelAttribute Korisnik korisnik, Model model) {
		Korisnik kor=korisnikService.findByKorisnickoImeAndLozinka(korisnik.getKorisnickoIme(), korisnik.getLozinka());
		if(kor==null) {
			return "greska.html";
		}else if(kor.isAktivan()){
			return "uspesno-logovanje.html";
		}else {
			return "greska.html";
		}
	}
	
	@GetMapping("/pretrazi-film")
	public String getFilm_po_nazivu(@ModelAttribute Film film, Model model) {
		Film filmm =this.filmService.find_by_naziv(film.getNaziv());
		model.addAttribute("filmovi", filmm);
		return "filmovi.html";
	}
	@GetMapping("/pretrazi-film-zanr")
	public String getFilm_po_zanru(@ModelAttribute Film film, Model model) {
		List<Film> filmList =this.filmService.find_by_zanr(film.getZanr());
		model.addAttribute("filmovi", filmList);
		return "filmovi.html";
	}
}
