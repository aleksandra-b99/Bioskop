package com.example.Bioskop.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.naming.ldap.SortKey;
import javax.xml.crypto.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bioskop.entity.Film;
import com.example.Bioskop.entity.Sala;
import com.example.Bioskop.entity.TerminskaLista;
import com.example.Bioskop.entity.dto.FilmDTO;
import com.example.Bioskop.entity.dto.SalaDTO;
import com.example.Bioskop.entity.dto.TerminskaListaDTO;
import com.example.Bioskop.repository.FilmRepository;
import com.example.Bioskop.service.FilmService;
import com.example.Bioskop.service.SalaService;
import com.example.Bioskop.service.TerminskaListaService;

@RestController
@RequestMapping(value="/api/terminskaLista")
public class TerminskaListaController {
	
	private final TerminskaListaService terminskaListaService;
	
	private final SalaService salaService;
	
	private final FilmRepository filmRepository;
	
	@Autowired
	public TerminskaListaController (FilmRepository filmRepository,TerminskaListaService terminskaListaService,SalaService salaService) {
		this.terminskaListaService=terminskaListaService;
		this.salaService=salaService;
		this.filmRepository=filmRepository;
	}
	@Autowired
	public FilmService filmService;
	@GetMapping(
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<TerminskaListaDTO>> getTermiskeListe(){
		List<TerminskaLista> terminskeListe=this.terminskaListaService.findAll();
		List<TerminskaListaDTO> terminskaListaDTOS= new ArrayList<>();
		for(TerminskaLista terminskaLista:terminskeListe) {
			TerminskaListaDTO terminskaListaDTO=new  TerminskaListaDTO(terminskaLista.getId(),terminskaLista.getDanProjekcije(),terminskaLista.getPocetak(),terminskaLista.getCena(),terminskaLista.getBrojRezervacija(),terminskaLista.getSala().getOznakaSale(),terminskaLista.getFilm().getNaziv(),terminskaLista.getFilm().getZanr(),terminskaLista.getSala().getKapacitet(),terminskaLista.getFilm().getTrajanje(),terminskaLista.getFilm().getOpis(),terminskaLista.getFilm().getOcena());
			terminskaListaDTOS.add(terminskaListaDTO);
		}
	return new ResponseEntity<>(terminskaListaDTOS, HttpStatus.OK);
	}
	@GetMapping(
			value="/detaljiProjekcije/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<TerminskaListaDTO> getTerminskaLista(@PathVariable(name="id")Long id){
		TerminskaLista terminskaLista=terminskaListaService.findOne(id);
		TerminskaListaDTO termiskaListaDTO=new TerminskaListaDTO(terminskaLista.getId(),terminskaLista.getDanProjekcije(),terminskaLista.getPocetak(),terminskaLista.getCena(),terminskaLista.getBrojRezervacija(),terminskaLista.getSala().getOznakaSale(),terminskaLista.getFilm().getNaziv(),terminskaLista.getFilm().getZanr(),terminskaLista.getSala().getKapacitet(),terminskaLista.getFilm().getTrajanje(),terminskaLista.getFilm().getOpis(),terminskaLista.getFilm().getOcena());
		return new ResponseEntity<>(termiskaListaDTO,HttpStatus.OK);
	}
	@GetMapping(
			value="naziv/{naziv}/{zanr}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<TerminskaListaDTO>> pretraga(@PathVariable(name="naziv")String naziv,@PathVariable(name="zanr")String zanr){
		Film film=filmService.findByNazivAndZanr(naziv, zanr);
		List<TerminskaLista> terminskeListe=terminskaListaService.findByFilmId(film.getId());
		List<TerminskaListaDTO> terminskaListaDTOS=new ArrayList<>();
		for(TerminskaLista terminskaLista:terminskeListe) {
			TerminskaListaDTO terminskaListaDTO=new TerminskaListaDTO(terminskaLista.getId(),terminskaLista.getDanProjekcije(),terminskaLista.getPocetak(),terminskaLista.getCena(),terminskaLista.getBrojRezervacija(),terminskaLista.getSala().getOznakaSale(),terminskaLista.getFilm().getNaziv(),terminskaLista.getFilm().getZanr(),terminskaLista.getSala().getKapacitet(),terminskaLista.getFilm().getTrajanje(),terminskaLista.getFilm().getOpis(),terminskaLista.getFilm().getOcena());
			terminskaListaDTOS.add(terminskaListaDTO);
		}
		return new ResponseEntity<>(terminskaListaDTOS,HttpStatus.OK);
	}
	@GetMapping(
			value="/naziv/{naziv}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<TerminskaListaDTO>> pretraga(@PathVariable(name="naziv")String naziv){
		Film film =filmService.find_by_naziv(naziv);
		List<TerminskaLista> terminskeListe=terminskaListaService.findByFilmId(film.getId());
		List<TerminskaListaDTO> terminskaListaDTOS=new ArrayList<>();
		for(TerminskaLista terminskaLista:terminskeListe) {
			TerminskaListaDTO terminskaListaDTO=new TerminskaListaDTO(terminskaLista.getId(),terminskaLista.getDanProjekcije(),terminskaLista.getPocetak(),terminskaLista.getCena(),terminskaLista.getBrojRezervacija(),terminskaLista.getSala().getOznakaSale(),terminskaLista.getFilm().getNaziv(),terminskaLista.getFilm().getZanr(),terminskaLista.getSala().getKapacitet(),terminskaLista.getFilm().getTrajanje(),terminskaLista.getFilm().getOpis(),terminskaLista.getFilm().getOcena());
			terminskaListaDTOS.add(terminskaListaDTO);
		}
		return new ResponseEntity<>(terminskaListaDTOS,HttpStatus.OK);
	}
	//pretraga po datumu i vremenu projekcije
	@GetMapping(
			value="/pocetak/{pocetak}/{datum}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<TerminskaListaDTO> pretragaPoPocetkuPoDatumu(@PathVariable(name="pocetak")String pocetak,@PathVariable(name="datum")Date datum){
		TerminskaLista terminskaLista=terminskaListaService.findByPocetakAndDatum(pocetak, datum);
		TerminskaListaDTO terminskaListaDTO=new TerminskaListaDTO(terminskaLista.getId(),terminskaLista.getDanProjekcije(),terminskaLista.getPocetak(),terminskaLista.getCena(),terminskaLista.getBrojRezervacija(),terminskaLista.getSala().getOznakaSale(),terminskaLista.getFilm().getNaziv(),terminskaLista.getFilm().getZanr(),terminskaLista.getSala().getKapacitet(),terminskaLista.getFilm().getTrajanje(),terminskaLista.getFilm().getOpis(),terminskaLista.getFilm().getOcena());
		return new ResponseEntity<>(terminskaListaDTO,HttpStatus.OK);
	}
	@GetMapping(
			value="/pocetak/{pocetak}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<TerminskaListaDTO> pretragaPoPocetku(@PathVariable(name="pocetak")String pocetak){
		TerminskaLista terminskaLista=terminskaListaService.findByPocetak(pocetak);
		TerminskaListaDTO terminskaListaDTO=new TerminskaListaDTO(terminskaLista.getId(),terminskaLista.getDanProjekcije(),terminskaLista.getPocetak(),terminskaLista.getCena(),terminskaLista.getBrojRezervacija(),terminskaLista.getSala().getOznakaSale(),terminskaLista.getFilm().getNaziv(),terminskaLista.getFilm().getZanr(),terminskaLista.getSala().getKapacitet(),terminskaLista.getFilm().getTrajanje(),terminskaLista.getFilm().getOpis(),terminskaLista.getFilm().getOcena());
		return new ResponseEntity<>(terminskaListaDTO,HttpStatus.OK);
	}
	@GetMapping(
			value="/sveTLuSali/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<TerminskaListaDTO>> getTLizSale(@PathVariable(name="id")Long id){
		Sala sala=salaService.findById(id);
		List<TerminskaLista> lista=sala.getTerminskaLista();
		List<TerminskaListaDTO> listaDTOS=new ArrayList<>();
		for(TerminskaLista terminskaLista: lista) {
			TerminskaListaDTO terminskaListaDTO=new TerminskaListaDTO(terminskaLista.getId(),terminskaLista.getDanProjekcije(),terminskaLista.getPocetak(),terminskaLista.getCena(),terminskaLista.getBrojRezervacija(),terminskaLista.getSala().getOznakaSale(),terminskaLista.getFilm().getNaziv(),terminskaLista.getFilm().getZanr(),terminskaLista.getSala().getKapacitet(),terminskaLista.getFilm().getTrajanje(),terminskaLista.getFilm().getOpis(),terminskaLista.getFilm().getOcena());
			listaDTOS.add(terminskaListaDTO);
		}
		return new ResponseEntity<>(listaDTOS,HttpStatus.OK);
	}
	@PostMapping(
			value="/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<TerminskaListaDTO> kreirajTL(@RequestBody TerminskaListaDTO terminskaListaDTO,@PathVariable(name="id")Long id){
		Film film=new Film(terminskaListaDTO.getNazivFilma(), terminskaListaDTO.getOpisFilma(), terminskaListaDTO.getZanrFilma(), terminskaListaDTO.getTrajanjeFilma(), terminskaListaDTO.getOcena());
		filmRepository.save(film);
		Sala sala=salaService.findById(id);
		TerminskaLista tl=new TerminskaLista(terminskaListaDTO.getDanProjekcije(), terminskaListaDTO.getPocetak(), terminskaListaDTO.getCena(), terminskaListaDTO.getBrojRezervacija(),film,sala);
		sala.getTerminskaLista().add(tl);
		salaService.save(sala);
		System.out.println(tl);
		TerminskaListaDTO tlDTO=new TerminskaListaDTO(tl.getId(), tl.getDanProjekcije(), tl.getPocetak(), tl.getCena(), tl.getBrojRezervacija(), tl.getSala().getOznakaSale(), tl.getFilm().getNaziv(), tl.getFilm().getZanr(), tl.getSala().getKapacitet(), tl.getFilm().getTrajanje(),tl.getFilm().getOpis(), tl.getFilm().getOcena());
		return new ResponseEntity<>(tlDTO,HttpStatus.OK);
	}
	@PostMapping(
			value="/izmeni/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<TerminskaListaDTO> izmeniTL(@PathVariable(name="id")Long id,@RequestBody TerminskaListaDTO terminskaListaDTO){
		TerminskaLista tl=terminskaListaService.findOne(id);
		tl.setCena(terminskaListaDTO.getCena());
		tl.setDanProjekcije(terminskaListaDTO.getDanProjekcije());
		tl.setPocetak(terminskaListaDTO.getPocetak());
		Film f=tl.getFilm();
		f.setOpis(terminskaListaDTO.getOpisFilma());
		f.setNaziv(terminskaListaDTO.getNazivFilma());
		f.setZanr(terminskaListaDTO.getZanrFilma());
		f.setTrajanje(terminskaListaDTO.getTrajanjeFilma());
		filmRepository.save(f);
		tl.setFilm(f);
		terminskaListaService.save(tl);
		TerminskaListaDTO tlDTO=new TerminskaListaDTO(tl.getId(), tl.getDanProjekcije(), tl.getPocetak(), tl.getCena(), tl.getBrojRezervacija(), tl.getSala().getOznakaSale(), tl.getFilm().getNaziv(), tl.getFilm().getZanr(), tl.getSala().getKapacitet(), tl.getFilm().getTrajanje(),tl.getFilm().getOpis(), tl.getFilm().getOcena());
		return new ResponseEntity<>(tlDTO,HttpStatus.OK);
	}
	//radi
	/*@PostMapping(
			value="/rezervacijaKarte/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<TerminskaListaDTO> rezervisiKartu(@PathVariable(name="id")Long id) {
		TerminskaLista terminskaLista=terminskaListaService.findOne(id);
		TerminskaListaDTO terminskaListaDTO=new TerminskaListaDTO(terminskaLista.getId(),terminskaLista.getDanProjekcije(),terminskaLista.getPocetak(),terminskaLista.getCena(),terminskaLista.getBrojRezervacija(),terminskaLista.getSala().getOznakaSale(),terminskaLista.getFilm().getNaziv(),terminskaLista.getFilm().getZanr(),terminskaLista.getSala().getKapacitet(),terminskaLista.getFilm().getTrajanje(),terminskaLista.getFilm().getOpis(),terminskaLista.getFilm().getOcena());
		if(terminskaListaDTO.getBrojRezervacija()<terminskaListaDTO.getKapacitetSale()) {
			return new  ResponseEntity<>(terminskaListaDTO,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}*//*
	@GetMapping(
			value="/sortNaziv",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<TerminskaListaDTO>> sortNaziv(){
		List<TerminskaLista> terminskeListe =terminskaListaService.sortNaziv();
		List<TerminskaListaDTO> listaDTOS=new ArrayList<>();
		for(TerminskaLista terminskaLista: terminskeListe) {
			TerminskaListaDTO terminskaListaDTO=new TerminskaListaDTO(terminskaLista.getId(),terminskaLista.getDanProjekcije(),terminskaLista.getPocetak(),terminskaLista.getCena(),terminskaLista.getBrojRezervacija(),terminskaLista.getSala().getOznakaSale(),terminskaLista.getFilm().getNaziv(),terminskaLista.getFilm().getZanr(),terminskaLista.getSala().getKapacitet(),terminskaLista.getFilm().getTrajanje(),terminskaLista.getFilm().getOpis(),terminskaLista.getFilm().getOcena());
			listaDTOS.add(terminskaListaDTO);
		}
		return new ResponseEntity<>(listaDTOS,HttpStatus.OK);
	}*/
	@PostMapping(
			value="/pretraga",
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<TerminskaListaDTO>> pretraga(@RequestBody TerminskaListaDTO terminskaListaDTO){
		List<TerminskaListaDTO> terminskaListaDTOS=new ArrayList<>(); 
		Film film1=filmService.find_by_naziv(terminskaListaDTO.getNazivFilma());
		List<TerminskaLista> terminskaLista1=terminskaListaService.pretraga(terminskaListaDTO.getPocetak(),terminskaListaDTO.getCena(),terminskaListaDTO.getDanProjekcije(),film1); 
		for(TerminskaLista terminskaLista: terminskaLista1) {
			TerminskaListaDTO terminskaListaDTO1=new TerminskaListaDTO(terminskaLista.getId(),terminskaLista.getDanProjekcije(),terminskaLista.getPocetak(),terminskaLista.getCena(),terminskaLista.getBrojRezervacija(),terminskaLista.getSala().getOznakaSale(),terminskaLista.getFilm().getNaziv(),terminskaLista.getFilm().getZanr(),terminskaLista.getSala().getKapacitet(),terminskaLista.getFilm().getTrajanje(),terminskaLista.getFilm().getOpis(),terminskaLista.getFilm().getOcena());
			terminskaListaDTOS.add(terminskaListaDTO1);
		}
		return new ResponseEntity<>(terminskaListaDTOS,HttpStatus.OK);
	}////////////////////////
	@GetMapping(
			value="/sortCena",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<TerminskaListaDTO>> sortCena(){
		List<TerminskaLista> terminskeListe=terminskaListaService.sortCenaKarte();
		List<TerminskaListaDTO> terminskaListaDTOS=new ArrayList<>(); 
		for(TerminskaLista terminskaLista: terminskeListe) {
			TerminskaListaDTO terminskaListaDTO1=new TerminskaListaDTO(terminskaLista.getId(),terminskaLista.getDanProjekcije(),terminskaLista.getPocetak(),terminskaLista.getCena(),terminskaLista.getBrojRezervacija(),terminskaLista.getSala().getOznakaSale(),terminskaLista.getFilm().getNaziv(),terminskaLista.getFilm().getZanr(),terminskaLista.getSala().getKapacitet(),terminskaLista.getFilm().getTrajanje(),terminskaLista.getFilm().getOpis(),terminskaLista.getFilm().getOcena());
			terminskaListaDTOS.add(terminskaListaDTO1);
		}
		return new ResponseEntity<>(terminskaListaDTOS,HttpStatus.OK);
 	}
}
