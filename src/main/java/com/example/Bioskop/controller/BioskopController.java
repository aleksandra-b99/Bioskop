package com.example.Bioskop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Bioskop.entity.Bioskop;
import com.example.Bioskop.service.BioskopService;

@Controller
public class BioskopController {
	@Autowired
	private BioskopService bioskopService;
	@GetMapping("/bioskopi")
	public String getBiskopi(Model model) {
		List<Bioskop> bioskopList=this.bioskopService.findAll();
		model.addAttribute("bioskopi", bioskopList);
		return "bioskopi.html";
	}
	@GetMapping("/add-bioskop")
    public String addBioskop(Model model) {
    	Bioskop bioskop = new Bioskop();
    	model.addAttribute("bioskop", bioskop);
    	return "add-bioskop.html";
    }
	@PostMapping("/save-bioskop")
    public String saveBioskop(@ModelAttribute Bioskop bioskop) {
    	this.bioskopService.save(bioskop);
    	return "redirect:/bioskopi";
    }
	@GetMapping("/delete/{id}")
    public String deleteBioskop(@PathVariable(name="id")Long id) {
    	this.bioskopService.delete(id);
    	return "redirect:/bioskopi";
    }
}