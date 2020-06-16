package com.example.Bioskop.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Bioskop.entity.Bioskop;
import com.example.Bioskop.entity.Menadzer;
import com.example.Bioskop.repository.BioskopRepository;


@Service
public class BioskopService {
	@Autowired
	private BioskopRepository bioskopRepository;
	
	public List<Bioskop> findAll() {
        List<Bioskop> bioskopi = this.bioskopRepository.findAll();
        return bioskopi;
    }
	public Bioskop findOne(Long id) {
        Bioskop bioskop = this.bioskopRepository.getOne(id);
        return bioskop;
    }
	public void delete(Long id) {
    	bioskopRepository.deleteById(id);
    }
	public Bioskop save(Bioskop bioskop) {
		return this.bioskopRepository.save(bioskop);
	}
	public List<Bioskop> findByMenadzer(Long id) {
        List<Bioskop> bioskopi = this.bioskopRepository.findAllByMenadzerId(id);
        return bioskopi;
    }
}
