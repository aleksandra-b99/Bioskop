package com.example.Bioskop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bioskop.entity.Bioskop;
import com.example.Bioskop.entity.Sala;
import com.example.Bioskop.entity.TerminskaLista;
import com.example.Bioskop.entity.dto.BioskopDTO;
import com.example.Bioskop.entity.dto.SalaDTO;
import com.example.Bioskop.entity.dto.TerminskaListaDTO;
import com.example.Bioskop.repository.SalaRepository;
import com.example.Bioskop.service.SalaService;

@RestController
@RequestMapping(value="/api/sale")
public class SalaController {
	
	private final SalaService salaService;
	
	@Autowired
	public SalaController(SalaService salaService) {
		this.salaService=salaService;
	}
	@GetMapping(
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<SalaDTO>> getSale(){
		List<Sala> salaList=this.salaService.findAll();
		List<SalaDTO> salaDTOS= new ArrayList<>();
		for(Sala sala:salaList) {
			SalaDTO salaDTO=new  SalaDTO(sala.getId(),sala.getKapacitet(),sala.getOznakaSale());
			salaDTOS.add(salaDTO);
		}
	return new ResponseEntity<>(salaDTOS, HttpStatus.OK);
	}
	@GetMapping(
			value="/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<SalaDTO> getsala(@PathVariable(name="id")Long id){
		Sala sala=salaService.findById(id);
		SalaDTO salaDTO=new SalaDTO(sala.getId(), sala.getKapacitet(), sala.getOznakaSale());
		return new ResponseEntity<>(salaDTO, HttpStatus.OK);
	}
	@GetMapping(
			value="/obrisi/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<SalaDTO> obrisiSalu(@PathVariable(name="id")Long id){
		Sala sala=salaService.findById(id);
		salaService.delete(sala);
		SalaDTO salaDTO = new SalaDTO(sala.getId(),sala.getKapacitet(),sala.getOznakaSale());
		return new ResponseEntity<>(salaDTO,HttpStatus.OK);
	}
	/*@PostMapping(
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<SalaDTO> createKorisnik(@RequestBody SalaDTO salaDTO) throws Exception{
		Sala sala=new Sala(salaDTO.getKapacitet(),salaDTO.getOznakaSale());
		Sala newSala=salaService.save(sala);
		SalaDTO newSalaDTO=new SalaDTO(newSala.getId(),newSala.getKapacitet(),newSala.getOznakaSale());
		return new ResponseEntity<>(newSalaDTO, HttpStatus.OK);
	}*/
	@GetMapping(
			value="/terminskaLista/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<TerminskaListaDTO>> getTerminskaLista(@PathVariable(name="id")Long id){
		Sala sala=salaService.findById(id);
		List<TerminskaLista> lista=sala.getTerminskaLista();
		List<TerminskaListaDTO> listaDTOS=new ArrayList<>();
		for(TerminskaLista terminskaLista: lista) {
			TerminskaListaDTO terminskaListaDTO=new TerminskaListaDTO(terminskaLista.getId(),terminskaLista.getDanProjekcije(),terminskaLista.getPocetak(),terminskaLista.getCena(),terminskaLista.getBrojRezervacija(),terminskaLista.getSala().getOznakaSale(),terminskaLista.getFilm().getNaziv(),terminskaLista.getFilm().getZanr(),terminskaLista.getSala().getKapacitet(),terminskaLista.getFilm().getTrajanje(),terminskaLista.getFilm().getOpis(),terminskaLista.getFilm().getOcena());
			listaDTOS.add(terminskaListaDTO);
		}
		return new ResponseEntity<>(listaDTOS,HttpStatus.OK);
	}
	
}
