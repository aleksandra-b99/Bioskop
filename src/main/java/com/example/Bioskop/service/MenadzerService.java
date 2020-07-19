package com.example.Bioskop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Bioskop.entity.Menadzer;
import com.example.Bioskop.repository.MenadzerRepository;

@Service
public class MenadzerService {
	@Autowired
    private MenadzerRepository menadzerRepository;
	
	public Menadzer findOne(Long id) {
		return menadzerRepository.getOne(id);
	}
	public Menadzer findByKorisnickoIme(String korisnickoIme) {
		return menadzerRepository.findByKorisnickoIme(korisnickoIme);
	}
	
}
