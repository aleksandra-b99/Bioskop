package com.example.Bioskop.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Bioskop.entity.Administrator;
import com.example.Bioskop.repository.AdministratorRepository;
import com.example.Bioskop.repository.BioskopRepository;


@Service
public class AdministratorService {
	@Autowired
	private AdministratorRepository administratorRepository;
	@Autowired
	private BioskopRepository bioskopRepository;
	
	public List<Administrator> findAll() {
        List<Administrator> administrator = this.administratorRepository.findAll();
        return administrator;
    }
	public Administrator findOne(Long id) {
		Administrator administrator = this.administratorRepository.getOne(id);
        return administrator;
    }
	
}
