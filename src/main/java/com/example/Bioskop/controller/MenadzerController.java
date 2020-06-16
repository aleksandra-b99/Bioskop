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
import com.example.Bioskop.entity.Menadzer;
import com.example.Bioskop.service.BioskopService;
import com.example.Bioskop.service.FilmService;
import com.example.Bioskop.service.MenadzerService;

@Controller
public class MenadzerController {
	@Autowired
	private MenadzerService menadzerService;
	@Autowired
	private BioskopService bioskopService;
	@Autowired
	private FilmService filmService;
	
	//da bi mogli da se izlizstaju bioskopi u kojima radi
	/*@GetMapping("/menadzeri/{id}")
    public String getBioskop(Model model, @PathVariable(name="id")Long id) {
        List<Bioskop> bioskopList = this.bioskopService.findByMenadzer(id);
        model.addAttribute("bioskopi", bioskopList);
        return "bioskopi.html";
    }*/
	
}