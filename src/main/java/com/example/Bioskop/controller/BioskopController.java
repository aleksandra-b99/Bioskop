package com.example.Bioskop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bioskop.entity.Bioskop;
import com.example.Bioskop.entity.Gledaoc;
import com.example.Bioskop.entity.Korisnik;
import com.example.Bioskop.entity.Menadzer;
import com.example.Bioskop.entity.Sala;
import com.example.Bioskop.entity.TerminskaLista;
import com.example.Bioskop.entity.dto.BioskopDTO;
import com.example.Bioskop.entity.dto.DodavanjeBioskopaDTO;
import com.example.Bioskop.entity.dto.DodavanjeSaleDTO;
import com.example.Bioskop.entity.dto.KorisnikDTO;
import com.example.Bioskop.entity.dto.SalaDTO;
import com.example.Bioskop.entity.dto.TerminskaListaDTO;
import com.example.Bioskop.service.BioskopService;
import com.example.Bioskop.service.KorisnikService;
import com.example.Bioskop.service.MenadzerService;
import com.example.Bioskop.service.SalaService;

@RestController
@RequestMapping(value="/api/bioskopi")
public class BioskopController {
	
	private final BioskopService bioskopService;
	
	private final KorisnikService korisnikService;
	
	private final MenadzerService menadzerService;
	
	@Autowired
	public BioskopController(BioskopService bioskopService,KorisnikService korisnikService,MenadzerService menadzerService) {
		this.bioskopService=bioskopService;
		this.korisnikService=korisnikService;
		this.menadzerService=menadzerService;
	}
	@Autowired
	private SalaService salaService;

	
	@GetMapping(
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<BioskopDTO>> getBioskopi(){
		List<Bioskop> bioskopList=bioskopService.findAll();
		List<BioskopDTO> bioskopDTOS= new ArrayList<>();
		for(Bioskop bioskop:bioskopList) {
			BioskopDTO bioskopDTO=new BioskopDTO(bioskop.getId(),bioskop.getNaziv(),bioskop.getAdresa(), bioskop.getBrojCentrale(),bioskop.getEmail());
			bioskopDTOS.add(bioskopDTO);
		}
	return new ResponseEntity<>(bioskopDTOS, HttpStatus.OK);
	}
	@GetMapping(
			value="/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<DodavanjeBioskopaDTO> jedanBioskop(@PathVariable(name="id")Long id){
		Bioskop bio=bioskopService.findOne(id);
		DodavanjeBioskopaDTO bioskopDTO=new DodavanjeBioskopaDTO(bio.getNaziv(), bio.getAdresa(), bio.getBrojCentrale(), bio.getEmail(), bio.getMenadzeri().getId());
		return new ResponseEntity<>(bioskopDTO,HttpStatus.OK);
	}
	@GetMapping(
			value="/sala/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<DodavanjeSaleDTO> jednaSala(@PathVariable(name="id")Long id){
		Sala sala=salaService.findById(id);
		DodavanjeSaleDTO salaDTO=new DodavanjeSaleDTO(sala.getOznakaSale(), sala.getKapacitet());
		return new ResponseEntity<>(salaDTO,HttpStatus.OK);
	}
	@GetMapping(
			value="/bioskopiMenadzera",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<BioskopDTO>> getBioskopiMenadzera(Authentication authentication){
		User trenutniKorisnik = (User)authentication.getPrincipal();
		Menadzer menadzer=menadzerService.findByKorisnickoIme(trenutniKorisnik.getUsername());
		List<Bioskop> bioskopList=menadzer.getBioskopi();
		List<BioskopDTO> bioskopDTOS= new ArrayList<>();
		for(Bioskop bioskop:bioskopList) {
			BioskopDTO bioskopDTO=new BioskopDTO(bioskop.getId(),bioskop.getNaziv(),bioskop.getAdresa(), bioskop.getBrojCentrale(),bioskop.getEmail());
			bioskopDTOS.add(bioskopDTO);
		}
	return new ResponseEntity<>(bioskopDTOS, HttpStatus.OK);
	}
	@PostMapping(
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<BioskopDTO> createKorisnik(@RequestBody DodavanjeBioskopaDTO dodavanjeBioskopaDTO) throws Exception{
		Menadzer menadzer=menadzerService.findOne(dodavanjeBioskopaDTO.getIdMenadzera());
		Bioskop bioskop=new Bioskop(dodavanjeBioskopaDTO.getNaziv(), dodavanjeBioskopaDTO.getAdresa(), dodavanjeBioskopaDTO.getBrojTelefona(), dodavanjeBioskopaDTO.getEmail(),menadzer);
		Bioskop newBioskop=bioskopService.save(bioskop);
		BioskopDTO newBioskopDTO= new BioskopDTO(newBioskop.getId(),newBioskop.getNaziv(),newBioskop.getAdresa(),newBioskop.getBrojCentrale(),newBioskop.getEmail());
		return new ResponseEntity<>(newBioskopDTO, HttpStatus.OK);
	}
	@PostMapping(
			value="/izmene/{id}",
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<BioskopDTO> izmeniBioskop(@RequestBody DodavanjeBioskopaDTO dodavanjeBioskopaDTO ,@PathVariable(name="id")Long id){
		Bioskop bioskop=bioskopService.findOne(id);
			bioskop.setNaziv(dodavanjeBioskopaDTO.getNaziv());
			bioskop.setAdresa(dodavanjeBioskopaDTO.getAdresa());
			bioskop.setBrojCentrale(dodavanjeBioskopaDTO.getBrojTelefona());
			bioskop.setEmail(dodavanjeBioskopaDTO.getEmail());
			Menadzer menadzer=menadzerService.findOne(dodavanjeBioskopaDTO.getIdMenadzera());
			bioskop.setMenadzeri(menadzer);
		
		bioskopService.save(bioskop);
		BioskopDTO newBioskopDTO=new BioskopDTO(bioskop.getId(),bioskop.getNaziv(), bioskop.getAdresa(), bioskop.getBrojCentrale(), bioskop.getEmail());
		return new ResponseEntity<>(newBioskopDTO,HttpStatus.OK);
	}
	@PostMapping(
			value="/izmeneSale/{id}",
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<SalaDTO> izmeniSalu(@RequestBody DodavanjeSaleDTO dodavanjeSaleDTO,@PathVariable(name="id")Long id){
		Sala sala=salaService.findById(id);
		sala.setOznakaSale(dodavanjeSaleDTO.getOznakaSale());
		sala.setKapacitet(dodavanjeSaleDTO.getKapacitet());
		salaService.save(sala);
		SalaDTO s=new SalaDTO(sala.getId(), sala.getKapacitet(), sala.getOznakaSale());
		return new ResponseEntity<>(s,HttpStatus.OK);
	}
	
	@GetMapping(
			value="/obrisi/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<BioskopDTO> obrisiBioskop(@PathVariable(name="id") Long id){
		Bioskop bioskop=bioskopService.findOne(id);
		bioskopService.delete(id);
		BioskopDTO bioskopDTO=new BioskopDTO(bioskop.getId(),bioskop.getNaziv(),bioskop.getAdresa(),bioskop.getBrojCentrale(),bioskop.getEmail());
		return new ResponseEntity<>(bioskopDTO,HttpStatus.OK);
	}
	@GetMapping(
			value="/sale/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<SalaDTO>> getSale(@PathVariable(name="id") Long id){
		Bioskop bioskop=bioskopService.findOne(id);
		List<SalaDTO> salaDTOS= new ArrayList<>();
		List<Sala> noveSale=bioskop.getSale();
		for(Sala sala: noveSale) {
			if(sala.getBioskop().getId()==id) {
				SalaDTO salaDTO=new SalaDTO(sala.getId(),sala.getKapacitet(),sala.getOznakaSale());
				salaDTOS.add(salaDTO);
			}
		}
		return new ResponseEntity<>(salaDTOS, HttpStatus.OK);
	}
	@PostMapping(
			value="/dodajSalu/{id}",//id bioskopa
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<SalaDTO> dodajSalu(@RequestBody DodavanjeSaleDTO dodavanjeSaleDTO,Authentication authentication,@PathVariable(name="id") Long id){
		Bioskop bioskop=bioskopService.findOne(id);
		Sala sala=new Sala(dodavanjeSaleDTO.getKapacitet(), dodavanjeSaleDTO.getOznakaSale(),bioskop);
		salaService.save(sala);
		bioskop.getSale().add(sala);
		bioskopService.save(bioskop);
		SalaDTO salaDTO=new SalaDTO(sala.getId(), sala.getKapacitet(), sala.getOznakaSale());
		return new ResponseEntity<>(salaDTO,HttpStatus.OK);
	}
	@GetMapping(
			value="/sale/terminskaLista/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<TerminskaListaDTO>> getTerminskeListe(@PathVariable(name="id") Long id){
		Sala sala=salaService.findById(id);
		List<TerminskaListaDTO> terminskaListaDTOS= new ArrayList<>();
		List<TerminskaLista> noveTerminskaLista=sala.getTerminskaLista();
		for(TerminskaLista terminskaLista: noveTerminskaLista) {
			TerminskaListaDTO terminskaListaDTO=new TerminskaListaDTO(terminskaLista.getId(),terminskaLista.getDanProjekcije(),terminskaLista.getPocetak(),terminskaLista.getCena(),terminskaLista.getBrojRezervacija(),terminskaLista.getSala().getOznakaSale(),terminskaLista.getFilm().getNaziv(),terminskaLista.getFilm().getZanr(),terminskaLista.getSala().getKapacitet(),terminskaLista.getFilm().getTrajanje(),terminskaLista.getFilm().getOpis(),terminskaLista.getFilm().getOcena());
			terminskaListaDTOS.add(terminskaListaDTO);
		}
		return new ResponseEntity<>(terminskaListaDTOS, HttpStatus.OK);
	}
}