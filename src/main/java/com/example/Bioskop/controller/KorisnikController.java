package com.example.Bioskop.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bioskop.entity.Bioskop;
import com.example.Bioskop.entity.Film;
import com.example.Bioskop.entity.Gledaoc;
import com.example.Bioskop.entity.Korisnik;
import com.example.Bioskop.entity.Menadzer;
import com.example.Bioskop.entity.Ocene_odgledanih_filmova;
import com.example.Bioskop.entity.TerminskaLista;
import com.example.Bioskop.entity.dto.BioskopDTO;
import com.example.Bioskop.entity.dto.FilmDTO;
import com.example.Bioskop.entity.dto.KorisnikDTO;
import com.example.Bioskop.entity.dto.OdgledanFilmDTO;
import com.example.Bioskop.entity.dto.OdgledaniFilmoviSaOcenomDTO;
import com.example.Bioskop.entity.dto.TerminskaListaDTO;
import com.example.Bioskop.entity.dto.UlogovaniKorisnik;
import com.example.Bioskop.repository.FilmRepository;
import com.example.Bioskop.repository.MenadzerRepository;
import com.example.Bioskop.repository.Ocene_odgledanih_filmovaRepository;
import com.example.Bioskop.service.FilmService;
import com.example.Bioskop.service.GledaocService;
import com.example.Bioskop.service.KorisnikService;
import com.example.Bioskop.service.TerminskaListaService;

@RestController
@RequestMapping(value="/api/korisnici")
public class KorisnikController {
	
	private final KorisnikService korisnikService; 
	
	private final PasswordEncoder encoder;
	
	private final TerminskaListaService terminskaListaService;
	
	private final GledaocService gledaocService;
	
	private final FilmService filmService;
	
	private final FilmRepository filmRepository;
	
	private MenadzerRepository menadzerRepository;
	
	private final Ocene_odgledanih_filmovaRepository ocene_odgledanih_filmovaRepository;
	
	@Autowired
	public KorisnikController(MenadzerRepository menadzerRepository,KorisnikService korisnikService,PasswordEncoder encoder,TerminskaListaService terminskaListaService,GledaocService gledaocService,FilmService filmService,Ocene_odgledanih_filmovaRepository ocene_odgledanih_filmovaRepository,FilmRepository filmRepository) {
		this.korisnikService=korisnikService;
		this.encoder=encoder;
		this.terminskaListaService=terminskaListaService;
		this.gledaocService=gledaocService;
		this.filmService=filmService;
		this.ocene_odgledanih_filmovaRepository=ocene_odgledanih_filmovaRepository;
		this.filmRepository=filmRepository;
		this.menadzerRepository=menadzerRepository;
	}
	//prikaz svih korisnika
	@GetMapping(
				produces=MediaType.APPLICATION_JSON_VALUE
				)
	public ResponseEntity<List<KorisnikDTO>> getKorisnici(){
		List<Korisnik> korisnikList=this.korisnikService.findAll();
		List<KorisnikDTO> korisnikDTOS= new ArrayList<>();
		for(Korisnik korisnik:korisnikList) {
			KorisnikDTO korisnikDTO=new KorisnikDTO(korisnik.getId(),korisnik.getIme(),korisnik.getPrezime(),korisnik.getKorisnickoIme(),korisnik.getLozinka(),korisnik.getKontaktTelefon(), korisnik.getEadresa(), korisnik.getDatumRodjenja(),korisnik.getUloga(),korisnik.isAktivan());
			korisnikDTOS.add(korisnikDTO);
		}
		return new ResponseEntity<>(korisnikDTOS, HttpStatus.OK);
	}
	//prikaz jednog korisnika
	@GetMapping(
			value="/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
    public ResponseEntity<KorisnikDTO> getKorisnik(@PathVariable(name = "id") Long id) {
    	Korisnik korisnik = this.korisnikService.findOne(id);
    	KorisnikDTO korisnikDTO=new KorisnikDTO();
    	korisnikDTO.setIme(korisnik.getIme());
    	korisnikDTO.setPrezime(korisnik.getPrezime());
    	korisnikDTO.setKorisnickoIme(korisnik.getKorisnickoIme());
    	korisnikDTO.setLozinka(korisnik.getLozinka());
    	korisnikDTO.setDatumRodjenja(korisnik.getDatumRodjenja());
    	korisnikDTO.setAktivan(korisnik.isAktivan());
    	korisnikDTO.setEadresa(korisnik.getEadresa());
    	korisnikDTO.setKontaktTelefon(korisnik.getKontaktTelefon());
    	korisnikDTO.setUloga(korisnik.getUloga());
    	return new ResponseEntity<>(korisnikDTO,HttpStatus.OK);
    }/*
	//kreiranje novog korisnika
		@PostMapping( consumes=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<KorisnikDTO> createKorisnik(@RequestBody KorisnikDTO korisnikDTO) throws Exception{
			Korisnik postojeciKorisnik = korisnikService.findByKorisnickoIme(korisnikDTO.getKorisnickoIme());
			if(postojeciKorisnik != null){
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}//korisnik vec postoji
			Korisnik korisnik=new Korisnik(korisnikDTO.getKorisnickoIme(),korisnikDTO.getIme(),korisnikDTO.getPrezime(),encoder.encode(korisnikDTO.getLozinka()),korisnikDTO.getKontaktTelefon(), korisnikDTO.getEadresa(), korisnikDTO.getDatumRodjenja(),korisnikDTO.getUloga(),true);
			if(korisnik.getUloga().equals("menadzer")) {
				korisnik.setAktivan(false);
			}
			Korisnik newKorisnik=korisnikService.save(korisnik);
			KorisnikDTO newKorisnikDTO=new KorisnikDTO(newKorisnik.getId(),newKorisnik.getIme(),newKorisnik.getPrezime(),newKorisnik.getKorisnickoIme(),newKorisnik.getLozinka(),newKorisnik.getKontaktTelefon(), newKorisnik.getEadresa(), newKorisnik.getDatumRodjenja(),newKorisnik.getUloga(),newKorisnik.isAktivan());
			return new ResponseEntity<>(newKorisnikDTO, HttpStatus.OK);
		}*/
		
		 	//kreiranje novog korisnika
		@PostMapping( 
				consumes=MediaType.APPLICATION_JSON_VALUE
				)
		public ResponseEntity<KorisnikDTO> createKorisnik(@RequestBody KorisnikDTO korisnikDTO) throws Exception{
			Korisnik postojeciKorisnik = korisnikService.findByKorisnickoIme(korisnikDTO.getKorisnickoIme());
			if(postojeciKorisnik != null){
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}//korisnik vec postoji
			Korisnik korisnik=new Korisnik(korisnikDTO.getKorisnickoIme(),korisnikDTO.getIme(),korisnikDTO.getPrezime(),encoder.encode(korisnikDTO.getLozinka()),korisnikDTO.getKontaktTelefon(), korisnikDTO.getEadresa(), korisnikDTO.getDatumRodjenja(),korisnikDTO.getUloga(),true);
			if(korisnik.getUloga().equals("menadzer")) {
				Menadzer menadzer=new Menadzer(korisnikDTO.getKorisnickoIme(),korisnikDTO.getIme(),korisnikDTO.getPrezime(),encoder.encode(korisnikDTO.getLozinka()),korisnikDTO.getKontaktTelefon(), korisnikDTO.getEadresa(), korisnikDTO.getDatumRodjenja(),korisnikDTO.getUloga(),true);
				menadzer.setAktivan(false);
				menadzerRepository.save(menadzer);
				KorisnikDTO newKorisnikDTO=new KorisnikDTO(menadzer.getId(),menadzer.getIme(),menadzer.getPrezime(),menadzer.getKorisnickoIme(),menadzer.getLozinka(),menadzer.getKontaktTelefon(), menadzer.getEadresa(), menadzer.getDatumRodjenja(),menadzer.getUloga(),menadzer.isAktivan());
				return new ResponseEntity<>(newKorisnikDTO, HttpStatus.OK);
			}
			if(korisnik.getUloga().equals("gledaoc")) {
				korisnik.setAktivan(true);
				Gledaoc gledaoc=new Gledaoc(korisnikDTO.getKorisnickoIme(),korisnikDTO.getIme(),korisnikDTO.getPrezime(),encoder.encode(korisnikDTO.getLozinka()),korisnikDTO.getKontaktTelefon(), korisnikDTO.getEadresa(), korisnikDTO.getDatumRodjenja(),korisnikDTO.getUloga(),true);
				
				gledaocService.save(gledaoc);
				KorisnikDTO newKorisnikDTO=new KorisnikDTO(gledaoc.getId(),gledaoc.getIme(),gledaoc.getPrezime(),gledaoc.getKorisnickoIme(),gledaoc.getLozinka(),gledaoc.getKontaktTelefon(), gledaoc.getEadresa(), gledaoc.getDatumRodjenja(),gledaoc.getUloga(),gledaoc.isAktivan());
				return new ResponseEntity<>(newKorisnikDTO, HttpStatus.OK);
			}
			//Korisnik newKorisnik=korisnikService.save(korisnik);
			//KorisnikDTO newKorisnikDTO=new KorisnikDTO(newKorisnik.getId(),newKorisnik.getIme(),newKorisnik.getPrezime(),newKorisnik.getKorisnickoIme(),newKorisnik.getLozinka(),newKorisnik.getKontaktTelefon(), newKorisnik.getEadresa(), newKorisnik.getDatumRodjenja(),newKorisnik.getUloga(),newKorisnik.isAktivan());
			//return new ResponseEntity<>(newKorisnikDTO, HttpStatus.OK);
			return new ResponseEntity<>( HttpStatus.OK);
		}
	@GetMapping(
			value="/deaktiviraj/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<KorisnikDTO> deaktiviraj(@PathVariable (name="id")Long id){
		Korisnik korisnik=korisnikService.findOne(id);
		if(korisnik.isAktivan()) {
			korisnik.setAktivan(false);
		}
		Korisnik newKorisnik=korisnikService.save(korisnik);
		KorisnikDTO korisnikDTO=new KorisnikDTO(newKorisnik.getId(),newKorisnik.getIme(),newKorisnik.getPrezime(),newKorisnik.getKorisnickoIme(),newKorisnik.getLozinka(),newKorisnik.getKontaktTelefon(), newKorisnik.getEadresa(), newKorisnik.getDatumRodjenja(),newKorisnik.getUloga(),newKorisnik.isAktivan()); 
		return new ResponseEntity<>(korisnikDTO, HttpStatus.OK);
	}
	@GetMapping(
			value="/aktiviraj/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<KorisnikDTO> aktiviraj(@PathVariable (name="id")Long id){
		Korisnik korisnik=korisnikService.findOne(id);
		if(!korisnik.isAktivan()) {
			korisnik.setAktivan(true);
		}
		Korisnik newKorisnik=korisnikService.save(korisnik);
		KorisnikDTO korisnikDTO=new KorisnikDTO(newKorisnik.getId(),newKorisnik.getIme(),newKorisnik.getPrezime(),newKorisnik.getKorisnickoIme(),newKorisnik.getLozinka(),newKorisnik.getKontaktTelefon(), newKorisnik.getEadresa(), newKorisnik.getDatumRodjenja(),newKorisnik.getUloga(),newKorisnik.isAktivan());
		return new ResponseEntity<>(korisnikDTO, HttpStatus.OK);
	}
	@GetMapping(
			value="/obrisi/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<KorisnikDTO> obrisiKorisnika(@PathVariable(name="id") Long id){
		Korisnik korisnik=korisnikService.findOne(id);
		korisnikService.delete(id);
		KorisnikDTO korisnikDTO=new KorisnikDTO(korisnik.getId(),korisnik.getIme(),korisnik.getPrezime(),korisnik.getKorisnickoIme(),korisnik.getLozinka(),korisnik.getKontaktTelefon(), korisnik.getEadresa(), korisnik.getDatumRodjenja(),korisnik.getUloga(),korisnik.isAktivan());
		return new ResponseEntity<>(korisnikDTO,HttpStatus.OK);
	}
	@GetMapping(
			value="/bioskopi/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<List<BioskopDTO>> getBioskopi(@PathVariable(name="id")Long id){
		Korisnik korisnik=korisnikService.findOne(id);
		List<BioskopDTO> bioskopDTOS=new ArrayList<>();
		Menadzer menadzer=new Menadzer();
		menadzer.setId(korisnik.getId());
		List<Bioskop> bioskopi=menadzer.getBioskopi();
		for(Bioskop bioskop:bioskopi) {
			BioskopDTO bioskopDTO=new BioskopDTO(bioskop.getId(),bioskop.getNaziv(),bioskop.getAdresa(),bioskop.getBrojCentrale(),bioskop.getEmail());
			bioskopDTOS.add(bioskopDTO);
		}
		return new ResponseEntity<>(bioskopDTOS,HttpStatus.OK);
	}
	//prikaz trenutno ulogovanog korisnika
		@GetMapping(value="/trenutni",
				produces=MediaType.APPLICATION_JSON_VALUE
				)
		public ResponseEntity<UlogovaniKorisnik> getTrenutniKorisnik(Authentication authentication) {
			User trenutniKorisnik = (User)authentication.getPrincipal();
			String korisnickoIme = trenutniKorisnik.getUsername();
			String uloga = trenutniKorisnik.getAuthorities().iterator().next().getAuthority().substring(5);

			return new ResponseEntity<>(new UlogovaniKorisnik(korisnickoIme, uloga),HttpStatus.OK);
		}
		@PostMapping(
				value="/rezervacijaKarte/{id}",
				produces=MediaType.APPLICATION_JSON_VALUE,
				consumes=MediaType.APPLICATION_JSON_VALUE
				)
		public ResponseEntity<TerminskaListaDTO> rezervisiKartu(@PathVariable(name="id")Long id,Authentication authentication) {
			TerminskaLista terminskaLista=terminskaListaService.findOne(id);
			User trenutniKorisnik = (User)authentication.getPrincipal();
			Gledaoc gledaoc=(Gledaoc) korisnikService.findByKorisnickoIme(trenutniKorisnik.getUsername());
			TerminskaListaDTO terminskaListaDTO=new TerminskaListaDTO(terminskaLista.getId(),terminskaLista.getDanProjekcije(),terminskaLista.getPocetak(),terminskaLista.getCena(),terminskaLista.getBrojRezervacija(),terminskaLista.getSala().getOznakaSale(),terminskaLista.getFilm().getNaziv(),terminskaLista.getFilm().getZanr(),terminskaLista.getSala().getKapacitet(),terminskaLista.getFilm().getTrajanje(),terminskaLista.getFilm().getOpis(),terminskaLista.getFilm().getOcena());
			if(terminskaListaDTO.getBrojRezervacija()<terminskaListaDTO.getKapacitetSale()) {
				terminskaLista.getZaineresovaniKorisnici().add(gledaoc);
				gledaoc.getRezervisaneTerminskeListe().add(terminskaLista);
				gledaocService.save(gledaoc);
				terminskaLista.setBrojRezervacija(terminskaListaDTO.getBrojRezervacija()+1);
				terminskaListaService.save(terminskaLista);
				terminskaListaDTO.setBrojRezervacija(terminskaLista.getBrojRezervacija());
				return new  ResponseEntity<>(terminskaListaDTO,HttpStatus.OK);
			}
			
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		@GetMapping(
				value="/pregledSvihRezervisanihKarata",
				produces=MediaType.APPLICATION_JSON_VALUE
				)
		public ResponseEntity<List<TerminskaListaDTO>> getRezervisaniTermini(Authentication authentication){
			User trenutniKorisnik = (User)authentication.getPrincipal();
			Gledaoc ulogovanGledaoc=(Gledaoc) korisnikService.findByKorisnickoIme(trenutniKorisnik.getUsername());
			List<TerminskaLista> terminskeListe=terminskaListaService.findAll();
			List<TerminskaListaDTO> terminskaListaDTOS=new ArrayList<>();
			for(TerminskaLista terminskaLista:terminskeListe) {
				List<Gledaoc> gledaoci =terminskaLista.getZaineresovaniKorisnici();
				for(Gledaoc gledaoc : gledaoci) {
					if(gledaoc.getId()==ulogovanGledaoc.getId()) {
						TerminskaListaDTO terminskaListaDTO=new TerminskaListaDTO(terminskaLista.getId(),terminskaLista.getDanProjekcije(),terminskaLista.getPocetak(),terminskaLista.getCena(),terminskaLista.getBrojRezervacija(),terminskaLista.getSala().getOznakaSale(),terminskaLista.getFilm().getNaziv(),terminskaLista.getFilm().getZanr(),terminskaLista.getSala().getKapacitet(),terminskaLista.getFilm().getTrajanje(),terminskaLista.getFilm().getOpis(),terminskaLista.getFilm().getOcena());
						terminskaListaDTOS.add(terminskaListaDTO);
					}
				}
			}
			return new ResponseEntity<>(terminskaListaDTOS,HttpStatus.OK);
		}
		@GetMapping(
				value="/mojprofil",
				produces=MediaType.APPLICATION_JSON_VALUE
				)
		public ResponseEntity<KorisnikDTO> profil(Authentication authentication){
			User trenutniKorisnik = (User)authentication.getPrincipal();
			Korisnik korisnik=korisnikService.findByKorisnickoIme(trenutniKorisnik.getUsername());
			KorisnikDTO korisnikDTO= new KorisnikDTO(korisnik.getId(),korisnik.getIme(),korisnik.getPrezime(),korisnik.getKorisnickoIme(),korisnik.getLozinka(),korisnik.getKontaktTelefon(),korisnik.getEadresa(),korisnik.getDatumRodjenja(),korisnik.getUloga(),korisnik.isAktivan());
			return new ResponseEntity<>(korisnikDTO,HttpStatus.OK);
		}
	
		@GetMapping(
				value="/ukloniRezervaciju/{id}",
				produces=MediaType.APPLICATION_JSON_VALUE
				)
		public ResponseEntity<TerminskaListaDTO> ukloniRezervaciju(Authentication authentication,@PathVariable(name="id")Long id){
			TerminskaLista terminskaLista=terminskaListaService.findOne(id);
			User trenutniKorisnik = (User)authentication.getPrincipal();
			Gledaoc ulogovanGledaoc=(Gledaoc) korisnikService.findByKorisnickoIme(trenutniKorisnik.getUsername());
			ulogovanGledaoc.removeTerminskaLista(terminskaLista);
			gledaocService.save(ulogovanGledaoc);
			TerminskaListaDTO terminskaListaDTO=new TerminskaListaDTO(terminskaLista.getId(),terminskaLista.getDanProjekcije(),terminskaLista.getPocetak(),terminskaLista.getCena(),terminskaLista.getBrojRezervacija(),terminskaLista.getSala().getOznakaSale(),terminskaLista.getFilm().getNaziv(),terminskaLista.getFilm().getZanr(),terminskaLista.getSala().getKapacitet(),terminskaLista.getFilm().getTrajanje(),terminskaLista.getFilm().getOpis(),terminskaLista.getFilm().getOcena());

			return new ResponseEntity<>(terminskaListaDTO,HttpStatus.OK);
		}
		@PostMapping(
				value="/kupiKartu/{id}",
				produces=MediaType.APPLICATION_JSON_VALUE
				//consumes=MediaType.APPLICATION_JSON_VALUE
				)
		public ResponseEntity<FilmDTO> kupiKartu(Authentication authentication,@PathVariable(name="id")Long id){
			User trenutniKorisnik = (User)authentication.getPrincipal();
			Gledaoc ulogovanGledaoc=(Gledaoc) korisnikService.findByKorisnickoIme(trenutniKorisnik.getUsername());
			TerminskaLista terminskaLista=terminskaListaService.findOne(id);
			ulogovanGledaoc.removeTerminskaLista(terminskaLista);
			Film film=filmService.findOne(terminskaLista.getFilm().getId());
			List<Film> filmovi=ulogovanGledaoc.getOdgledaniFilmovi();
			List<Film> sviFilmovi=ulogovanGledaoc.getSviOdgledaniFilmovi();
			List<Ocene_odgledanih_filmova> ocenjeni=ulogovanGledaoc.getOcene_odgledanih_filmova();
			ulogovanGledaoc.getRezervisaneTerminskeListe().remove(terminskaLista);
			if(!filmovi.contains(film)){
				for(Ocene_odgledanih_filmova o:ocenjeni) {
					if(o.getNaziv()==film.getNaziv()) {//film je vec ocenjen pa nece biti u ne ocenjenim
						gledaocService.save(ulogovanGledaoc);
						FilmDTO filmDTO=new FilmDTO(terminskaLista.getFilm().getId(), terminskaLista.getFilm().getNaziv(), terminskaLista.getFilm().getOpis(), terminskaLista.getFilm().getZanr(), terminskaLista.getFilm().getTrajanje(), terminskaLista.getFilm().getOcena());
						return new ResponseEntity<>(filmDTO,HttpStatus.OK);
					}
				}
				ulogovanGledaoc.getOdgledaniFilmovi().add(film);
				//gledaocService.save(ulogovanGledaoc);
			}
			if(!sviFilmovi.contains(film)) {
				ulogovanGledaoc.getSviOdgledaniFilmovi().add(film);
			}
			gledaocService.save(ulogovanGledaoc);
			FilmDTO filmDTO=new FilmDTO(terminskaLista.getFilm().getId(), terminskaLista.getFilm().getNaziv(), terminskaLista.getFilm().getOpis(), terminskaLista.getFilm().getZanr(), terminskaLista.getFilm().getTrajanje(), terminskaLista.getFilm().getOcena());
			return new ResponseEntity<>(filmDTO,HttpStatus.OK);
		}
		@GetMapping(
				value="/odgledaniFilmovi",
				produces=MediaType.APPLICATION_JSON_VALUE
				)
		public ResponseEntity<List<FilmDTO>> getOdgledaniFilmovi(Authentication authentication){
			User trenutniKorisnik = (User)authentication.getPrincipal();
			Gledaoc ulogovanGledaoc=(Gledaoc) korisnikService.findByKorisnickoIme(trenutniKorisnik.getUsername());
			List<Film> filmovi=ulogovanGledaoc.getOdgledaniFilmovi();
			List<FilmDTO> filmDTOS=new ArrayList<>();
			for(Film film:filmovi) {
				FilmDTO filmDTO=new FilmDTO(film.getId(), film.getNaziv(), film.getOpis(), film.getZanr(), film.getTrajanje(), film.getOcena());
				filmDTOS.add(filmDTO);
			}
			return new ResponseEntity<>(filmDTOS, HttpStatus.OK);
		}
		@PostMapping(
				value="/oceniFilm/{id}",
				produces=MediaType.APPLICATION_JSON_VALUE,
				consumes=MediaType.APPLICATION_JSON_VALUE
				)
		public ResponseEntity<FilmDTO> getFilm(Authentication authentication, @PathVariable(name="id")Long id,@RequestBody Integer ocena ){
			Film film=filmService.findOne(id);
			User trenutniKorisnik = (User)authentication.getPrincipal();
			Gledaoc ulogovanGledaoc=(Gledaoc) korisnikService.findByKorisnickoIme(trenutniKorisnik.getUsername());
			Ocene_odgledanih_filmova o=new Ocene_odgledanih_filmova(film.getNaziv(), ocena, ulogovanGledaoc);
			ulogovanGledaoc.getOdgledaniFilmovi().remove(film);
			
			List<Ocene_odgledanih_filmova> listaOcenjenih=ulogovanGledaoc.getOcene_odgledanih_filmova();
			for(Ocene_odgledanih_filmova ocenjeni: listaOcenjenih) {
				if(ocenjeni.getNaziv()==film.getNaziv()) {
					gledaocService.save(ulogovanGledaoc);
					FilmDTO f=new FilmDTO(film.getId(), film.getNaziv(), film.getOpis(), film.getZanr(), film.getTrajanje(),film.getOcena());
					return new ResponseEntity<>(f,HttpStatus.OK);
				}
			}
			ulogovanGledaoc.getOcene_odgledanih_filmova().add(o);
			ocene_odgledanih_filmovaRepository.save(o);
			gledaocService.save(ulogovanGledaoc);
			ocene_odgledanih_filmovaRepository.save(o);
			List<Gledaoc> gledaoci=gledaocService.findAll();
			int suma=0;
			double i=0;
			for(Gledaoc g:gledaoci) {
				List<Ocene_odgledanih_filmova> ocenjeniFilmovi=g.getOcene_odgledanih_filmova();
				for(Ocene_odgledanih_filmova o1:ocenjeniFilmovi) {
					if(o1.getNaziv()==film.getNaziv()) {
						suma=suma+o1.getOcena();
						i++;
					}
				}
			}
			film.setOcena(suma/i);
			filmRepository.save(film);
			//film.setOcena((film.getOcena()+ocena)/);
			FilmDTO f=new FilmDTO(film.getId(), film.getNaziv(), film.getOpis(), film.getZanr(), film.getTrajanje(),film.getOcena());
			return new ResponseEntity<>(f,HttpStatus.OK);
		}
		@GetMapping(
				value="/ocenjeniFilmovi",
				produces=MediaType.APPLICATION_JSON_VALUE
				)
		public ResponseEntity<List<OdgledaniFilmoviSaOcenomDTO>> getOcenjeniFilmovi(Authentication authentication){
			User trenutniKorisnik = (User)authentication.getPrincipal();
			Gledaoc ulogovanGledaoc=(Gledaoc) korisnikService.findByKorisnickoIme(trenutniKorisnik.getUsername());
			List<Ocene_odgledanih_filmova> odgledaniFilmovi =ulogovanGledaoc.getOcene_odgledanih_filmova();
			List<OdgledaniFilmoviSaOcenomDTO> odgledaniFilmoviSaOcenomDTOS=new ArrayList<>();
			for(Ocene_odgledanih_filmova film: odgledaniFilmovi) {
				OdgledaniFilmoviSaOcenomDTO o=new OdgledaniFilmoviSaOcenomDTO(film.getNaziv(), film.getOcena());
				odgledaniFilmoviSaOcenomDTOS.add(o);
			}
			return new ResponseEntity<>(odgledaniFilmoviSaOcenomDTOS,HttpStatus.OK);
		}
		@GetMapping(
				value="/sviOdgledaniFilmovi",
				produces=MediaType.APPLICATION_JSON_VALUE
				)
		public ResponseEntity<List<FilmDTO>> getSviOdgledaniFilmovi(Authentication authentication){
			User trenutniKorisnik = (User)authentication.getPrincipal();
			Gledaoc ulogovanGledaoc=(Gledaoc) korisnikService.findByKorisnickoIme(trenutniKorisnik.getUsername());
			List<Film> sviodgledaniFilmovi =ulogovanGledaoc.getSviOdgledaniFilmovi();
			List<FilmDTO> filmDTOS=new ArrayList<>();
			for(Film film: sviodgledaniFilmovi) {
				FilmDTO f=new FilmDTO(film.getId(), film.getNaziv(), film.getOpis(), film.getZanr(), film.getTrajanje(), film.getOcena());
				filmDTOS.add(f);
			}
			return new ResponseEntity<>(filmDTOS,HttpStatus.OK);
		}
		@GetMapping(
				value="/menadzeri",
				produces=MediaType.APPLICATION_JSON_VALUE
				)
	public ResponseEntity<List<KorisnikDTO>> getMenadzeri(){
		List<Korisnik> korisnikList=this.korisnikService.findAllMenadzeri("menadzer");
		List<KorisnikDTO> korisnikDTOS= new ArrayList<>();
		for(Korisnik korisnik:korisnikList) {
			KorisnikDTO korisnikDTO=new KorisnikDTO(korisnik.getId(),korisnik.getIme(),korisnik.getPrezime(),korisnik.getKorisnickoIme(),korisnik.getLozinka(),korisnik.getKontaktTelefon(), korisnik.getEadresa(), korisnik.getDatumRodjenja(),korisnik.getUloga(),korisnik.isAktivan());
			korisnikDTOS.add(korisnikDTO);
		}
		return new ResponseEntity<>(korisnikDTOS, HttpStatus.OK);
	}
		@GetMapping(
				value="/gledaoci",
				produces=MediaType.APPLICATION_JSON_VALUE
				)
	public ResponseEntity<List<KorisnikDTO>> getGledaoci(){
		List<Korisnik> korisnikList=this.korisnikService.findAllMenadzeri("gledaoc");
		List<KorisnikDTO> korisnikDTOS= new ArrayList<>();
		for(Korisnik korisnik:korisnikList) {
			KorisnikDTO korisnikDTO=new KorisnikDTO(korisnik.getId(),korisnik.getIme(),korisnik.getPrezime(),korisnik.getKorisnickoIme(),korisnik.getLozinka(),korisnik.getKontaktTelefon(), korisnik.getEadresa(), korisnik.getDatumRodjenja(),korisnik.getUloga(),korisnik.isAktivan());
			korisnikDTOS.add(korisnikDTO);
		}
		return new ResponseEntity<>(korisnikDTOS, HttpStatus.OK);
	}
}