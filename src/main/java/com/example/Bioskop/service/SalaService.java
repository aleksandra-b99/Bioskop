package com.example.Bioskop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Bioskop.entity.Sala;
import com.example.Bioskop.repository.SalaRepository;

@Service
public class SalaService {

	@Autowired
	private SalaRepository  salaRepository;
	
	public List<Sala> findAll(){
		return salaRepository.findAll();
	}
	public void delete(Sala sala) {
		salaRepository.delete(sala);
	}
	public Sala findById(Long id) {
		Sala sala=salaRepository.getOne(id);
		return sala;
	}
	public Sala save(Sala sala) {
		return salaRepository.save(sala);
	}
	public Sala findByNaziv(String naziv) {
		return salaRepository.findByOznakaSale(naziv);
	}
}
