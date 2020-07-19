package com.example.Bioskop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Bioskop.entity.Gledaoc;
import com.example.Bioskop.repository.GledaocRepository;
@Service
public class GledaocService {
	@Autowired
	private GledaocRepository gledaocRepository;
	
	public Gledaoc findByKorisnickoIme(String korisnickoIme) {
		return gledaocRepository.findByKorisnickoIme(korisnickoIme); 
	}
	public void save(Gledaoc gledaoc) {
		gledaocRepository.save(gledaoc);
	}
	public List<Gledaoc> findAll(){
		return gledaocRepository.findAll();
	}
}
