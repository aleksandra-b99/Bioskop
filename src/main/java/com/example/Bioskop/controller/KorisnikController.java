package com.example.Bioskop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
}