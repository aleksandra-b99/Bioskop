package com.example.Bioskop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Bioskop.entity.Bioskop;
import com.example.Bioskop.entity.Film;
import com.example.Bioskop.entity.Korisnik;
import com.example.Bioskop.entity.Menadzer;
import com.example.Bioskop.service.AdministratorService;
import com.example.Bioskop.service.BioskopService;
import com.example.Bioskop.service.FilmService;
import com.example.Bioskop.service.KorisnikService;
import com.example.Bioskop.service.MenadzerService;

@Controller
public class AdministratorController {
	@Autowired
	private AdministratorService administratorService;
	@Autowired
	private BioskopService bioskopService;
	@Autowired
	private MenadzerService menadzerService;
	@Autowired
	private FilmService filmService;
	@Autowired
	private KorisnikService korisnikService;
	
	//@Autowired
	//private PasswordEncoder passwordEncoder;
	
	//********************za korisnike***********************
	
	@GetMapping("/korisnici")
    public String getKorisnici(Model model) {
        List<Korisnik> korisnikList = this.korisnikService.findAll();
        model.addAttribute("korisnici", korisnikList);
        return "korisnici.html";
    }
	@GetMapping("/profil/{id}")
    public String getKorisnik(@PathVariable(name = "id") Long id, Model model) {
    	Korisnik korisnik = this.korisnikService.findOne(id);
    	model.addAttribute("korisnik", korisnik);
    	return "korisnik.html";
    }
	@GetMapping("/add-korisnik")
    public String addKorisnik() {
    	//Korisnik korisnik = new Korisnik();
    	//model.addAttribute("korisnik", korisnik);
    	return "registar.html";
    }
	@PostMapping("/save-korisnik")
    public String saveKorisnik(Korisnik korisnik) {
		if(korisnik.getUloga().equals("gledaoc")) {
			korisnik.setAktivan(true);
		}
		//korisnik.setLozinka(passwordEncoder.encode(korisnik.getLozinka()));
    	this.korisnikService.save(korisnik);
    	return "redirect:/korisnici";
    }
	@GetMapping("/delete-korisnik/{id}")
    public String deleteKorisnik(@PathVariable(name="id")Long id) {
    	this.korisnikService.delete(id);
    	return "redirect:/korisnici";
    }
	@GetMapping("/korisnici/{uloga}")
	public String getMenadzeri(@PathVariable(name="uloga")String uloga,Model model) {
		List<Korisnik> menadzeri =korisnikService.findAllMenadzeri(uloga);
		model.addAttribute("korisnici", menadzeri);
		return "korisnici.html";
	}
	//**********************filmovi******************
	@GetMapping("/svi_filmovi")
	public String getFilmovi(Model model) {
		List<Film> filmList = this.filmService.findAll();
        model.addAttribute("filmovi", filmList);
		return "filmovi.html";
	}
	@GetMapping("/aktiviraj-korisnika/{id}")
	public String aktivacija(@PathVariable(name="id")Long id, Model model) {
		Korisnik kor=korisnikService.findOne(id);
		if(!kor.isAktivan()) {//ako korisnik nije aktivan
			kor.setAktivan(true);//aktiviramo ga
		}
		korisnikService.save(kor);
		model.addAttribute("korisnici",kor);
		return "redirect:/korisnici";
	}
	@GetMapping("/deaktiviraj-korisnika/{id}")
	public String deaktiviraj(@PathVariable(name="id")Long id, Model model) {
		Korisnik kor=korisnikService.findOne(id);
		if(kor.isAktivan()) {
			kor.setAktivan(false);
		}
		korisnikService.save(kor);
		model.addAttribute("korisnik", kor);
		return "redirect:/korisnici";
	}
}
