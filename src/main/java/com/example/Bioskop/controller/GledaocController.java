package com.example.Bioskop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bioskop.entity.TerminskaLista;
import com.example.Bioskop.entity.dto.TerminskaListaDTO;
import com.example.Bioskop.repository.TerminskaListaRepository;
import com.example.Bioskop.service.GledaocService;
import com.example.Bioskop.service.TerminskaListaService;


@RestController
@RequestMapping(value = "/api/gledaoci")
public class GledaocController {
	@Autowired
	private GledaocService gledaocService;
	
	@Autowired
	private TerminskaListaService terminskaListaService;
	

}
