package com.example.Bioskop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bioskop.entity.Film;
import com.example.Bioskop.entity.Sala;
import com.example.Bioskop.entity.TerminskaLista;
import com.example.Bioskop.entity.dto.FilmDTO;
import com.example.Bioskop.entity.dto.SalaDTO;
import com.example.Bioskop.entity.dto.TerminskaListaDTO;
import com.example.Bioskop.service.FilmService;
import com.example.Bioskop.service.TerminskaListaService;


@RestController
@RequestMapping(value="/api/filmovi")
public class FilmController {
	
	private final FilmService filmService;
	
	@Autowired
	public FilmController(FilmService filmService) {
		this.filmService=filmService;
	}
	@Autowired
	private  TerminskaListaService terminskaListaService;
	
	/*@Autowired
	public FilmController(TerminskaListaService terminskaListaService) {
		this.terminskaListaService=terminskaListaService;
	}*/
	@GetMapping(
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<FilmDTO>> getFilmovi(){
		List<Film> filmovi=filmService.findAll();
		List<FilmDTO> filmDTOS=new ArrayList<>();
		for(Film film:filmovi) {
			FilmDTO filmDTO=new FilmDTO(film.getId(),film.getNaziv(),film.getOpis(),film.getZanr(),film.getTrajanje(),film.getOcena());
			filmDTOS.add(filmDTO);
		}
		return new ResponseEntity<>(filmDTOS,HttpStatus.OK);
	}
	@GetMapping(
			value="/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<FilmDTO> getFilm(@PathVariable(name="id")Long id){
		Film film=filmService.findOne(id);
		FilmDTO  filmDTO=new FilmDTO(film.getId(),film.getNaziv(),film.getOpis(),film.getZanr(),film.getTrajanje(),film.getOcena());
		return new ResponseEntity<>(filmDTO,HttpStatus.OK);
	}
	@GetMapping(
			value="/naziv/{naziv}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<FilmDTO> getFilmByNaziv(@PathVariable(name="naziv")String naziv){
		Film film=filmService.find_by_naziv(naziv);
		FilmDTO  filmDTO=new FilmDTO(film.getId(),film.getNaziv(),film.getOpis(),film.getZanr(),film.getTrajanje(),film.getOcena());
		return new ResponseEntity<>(filmDTO,HttpStatus.OK);
	}
	@GetMapping(
			value="/zanr/{zanr}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<FilmDTO>> getFilmByZanr(@PathVariable(name="zanr")String zanr){
		List<Film> filmovi=filmService.find_by_zanr(zanr);
		List<FilmDTO> filmDTOS=new ArrayList<>();
		for(Film film: filmovi) {
			FilmDTO  filmDTO=new FilmDTO(film.getId(),film.getNaziv(),film.getOpis(),film.getZanr(),film.getTrajanje(),film.getOcena());
			filmDTOS.add(filmDTO);
		}
		return new ResponseEntity<>(filmDTOS,HttpStatus.OK);
	}
	@GetMapping(
			value="/ocena/{ocena}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<FilmDTO>> getFilmByOcena(@PathVariable(name="ocena")Double ocena){
		List<Film> filmovi=filmService.findByOcena(ocena);
		List<FilmDTO> filmDTOS=new ArrayList<>();
		for(Film film: filmovi) {
			FilmDTO  filmDTO=new FilmDTO(film.getId(),film.getNaziv(),film.getOpis(),film.getZanr(),film.getTrajanje(),film.getOcena());
			filmDTOS.add(filmDTO);
		}
		return new ResponseEntity<>(filmDTOS,HttpStatus.OK);
	}
/*	@GetMapping(
			value="/terminskaLista/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<TerminskaListaDTO>> getTerminskeListe(@PathVariable(name="id")Long id){
		Film film=filmService.findOne(id);
		List<TerminskaListaDTO> terminskaListaDTOS=new ArrayList<>();
		List<TerminskaLista> terminskeListe=terminskaListaService.findByFilmId(film);
		for(TerminskaLista terminskaLista: terminskeListe) {
			TerminskaListaDTO  terminskaListaDTO= new TerminskaListaDTO(terminskaLista.getId(),terminskaLista.getFilm(),terminskaLista.getDanProjekcije(),terminskaLista.getPocetak(),terminskaLista.getCena(),terminskaLista.getBrojRezervacija()); 
			terminskaListaDTOS.add(terminskaListaDTO);
		}
		return new ResponseEntity<>(terminskaListaDTOS, HttpStatus.OK);
	}*/
	/*@GetMapping(
			value="/{naziv}/{zanr}/{ocena}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<FilmDTO> getFilmNazivZanrOcena(@PathVariable(name="naziv")String naziv,@PathVariable(name="zanr")String zanr,@PathVariable(name="ocena")Double ocena){
		
	}*/
	/*@GetMapping(
			value="/terminskaLista/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<TerminskaListaDTO>> getTerminskeListe(@PathVariable(name="id")Long id){
		Film film=filmService.findOne(id);
		List<Sala> sale=film.getSale();
		List<TerminskaListaDTO> terminskaListaDTOS= new ArrayList<>();
		for(Sala sala:sale) {
			List<TerminskaLista> terminskeListe= sala.getTerminskaLista();
			for(TerminskaLista terminskaLista: terminskeListe) {
				if(terminskaLista.getNaziv_filma()==film.getNaziv()) {
					TerminskaListaDTO terminskaListaDTO=new TerminskaListaDTO(terminskaLista.getId(),terminskaLista.getNaziv_filma(),terminskaLista.getDanProjekcije(),terminskaLista.getPocetak(),terminskaLista.getCena(),terminskaLista.getBrojRezervacija());
					terminskaListaDTOS.add(terminskaListaDTO);
				}
			}
		}
		return new ResponseEntity<>(terminskaListaDTOS,HttpStatus.OK);
		
	}*/
	/*@GetMapping(
			value="/sale/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<SalaDTO>> getSaleFilma(@PathVariable(name="id")Long id){
		Film film=filmService.findOne(id);
		List<Sala> sale=film.getSale();
		List<SalaDTO> salaDTOS=new ArrayList<>();
		for(Sala sala: sale) {
			if(sala.getId())
			}
		return new ResponseEntity<>(salaDTOS,HttpStatus.OK);
	}*/
	@GetMapping(
			value="/sortNaziv",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<FilmDTO>> sortNaziv(){
		List<Film> filmovi=filmService.sortNaziv();
		
		List<FilmDTO> filmDTOS=new ArrayList<>();
		for(Film film: filmovi) {
			FilmDTO  filmDTO=new FilmDTO(film.getId(),film.getNaziv(),film.getOpis(),film.getZanr(),film.getTrajanje(),film.getOcena());
			filmDTOS.add(filmDTO);
		}
		return new ResponseEntity<>(filmDTOS,HttpStatus.OK);
 	}
	@GetMapping(
			value="/sortZanr",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<FilmDTO>> sortZanr(){
		List<Film> filmovi=filmService.sortZanr();
		
		List<FilmDTO> filmDTOS=new ArrayList<>();
		for(Film film: filmovi) {
			FilmDTO  filmDTO=new FilmDTO(film.getId(),film.getNaziv(),film.getOpis(),film.getZanr(),film.getTrajanje(),film.getOcena());
			filmDTOS.add(filmDTO);
		}
		return new ResponseEntity<>(filmDTOS,HttpStatus.OK);
 	}
	@GetMapping(
			value="/sortOcena",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<FilmDTO>> sortOcena(){
		List<Film> filmovi=filmService.sortOcena();
		
		List<FilmDTO> filmDTOS=new ArrayList<>();
		for(Film film: filmovi) {
			FilmDTO  filmDTO=new FilmDTO(film.getId(),film.getNaziv(),film.getOpis(),film.getZanr(),film.getTrajanje(),film.getOcena());
			filmDTOS.add(filmDTO);
		}
		return new ResponseEntity<>(filmDTOS,HttpStatus.OK);
 	}
}