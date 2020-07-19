package com.example.Bioskop.service;

import java.sql.Date;
import java.util.List;

import javax.management.loading.ClassLoaderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.Bioskop.entity.Film;
import com.example.Bioskop.entity.Gledaoc;
import com.example.Bioskop.entity.TerminskaLista;
import com.example.Bioskop.entity.dto.TerminskaListaParameters;
import com.example.Bioskop.entity.dto.TerminskaListaSpecs;
import com.example.Bioskop.repository.TerminskaListaRepository;
import static com.example.Bioskop.entity.dto.TerminskaListaSpecs.*;

@Service
public class TerminskaListaService {

	@Autowired
	private  TerminskaListaRepository terminskaListaRepository;
	
	
	
	public List<TerminskaLista> findAll(){
		return terminskaListaRepository.findAll();
	}
	public TerminskaLista findOne(Long id) {
		return terminskaListaRepository.getOne(id);
	}
	public List<TerminskaLista> findByFilmId(Long id) {
		return terminskaListaRepository.findAllByFilmId(id);
	}
	public TerminskaLista findByNazivAndZanr(String naziv, String zanr) {
		return terminskaListaRepository.findByNazivAndZanr(naziv, zanr);
	}
	public TerminskaLista findByNaziv(String naziv) {
		return terminskaListaRepository.findByNaziv(naziv);
	}
	public TerminskaLista findByPocetakAndDatum(String pocetak, Date datum) {
		return terminskaListaRepository.findByPocetakAndDanProjekcije(pocetak, datum);
	}
	public TerminskaLista findByPocetak(String pocetak) {
		return terminskaListaRepository.findByPocetak(pocetak);
	}
	public void save(TerminskaLista terminskaLista) {
		terminskaListaRepository.save(terminskaLista);
	}
	public void delete(TerminskaLista terminskaLista) {
		terminskaListaRepository.delete(terminskaLista);
	}
	public List<TerminskaLista> sortNaziv() {
		return terminskaListaRepository.findAllByOrderByNaziv();
	}
	public List<TerminskaLista> pretraga(String pocetak, String cena,String danProjekcije,Film film) {
		if(pocetak!="" && cena!="" && danProjekcije!="" && film!=null) {
			return terminskaListaRepository.findAll(pocetakEquals(pocetak).and(cenaEquals(cena)).and(danProjekcijeEquals(danProjekcije)).and(filmEquals(film)));
		}else if(pocetak!="" && cena!="" && danProjekcije!="" && film==null) {
			return terminskaListaRepository.findAll(pocetakEquals(pocetak).and(cenaEquals(cena)).and(danProjekcijeEquals(danProjekcije)));
		}else if(pocetak!="" && cena!="" && danProjekcije=="" && film!=null) {
			return terminskaListaRepository.findAll(pocetakEquals(pocetak).and(cenaEquals(cena)).and(filmEquals(film)));
		}else if(pocetak!="" && cena=="" && danProjekcije!="" && film!=null) {
			return terminskaListaRepository.findAll(pocetakEquals(pocetak).and(danProjekcijeEquals(danProjekcije)).and(filmEquals(film)));
		}else if(pocetak=="" && cena!="" && danProjekcije!="" && film!=null) {
			return terminskaListaRepository.findAll(danProjekcijeEquals(danProjekcije).and(filmEquals(film)).and(cenaEquals(cena)));
		}else if(pocetak=="" && cena=="" && danProjekcije!="" && film!=null) {
			return terminskaListaRepository.findAll(danProjekcijeEquals(danProjekcije).and(filmEquals(film)));
		}else if(pocetak=="" && cena!="" && danProjekcije=="" && film!=null) {
			return terminskaListaRepository.findAll(cenaEquals(cena).and(filmEquals(film)));
		}else if(pocetak=="" && cena!="" && danProjekcije!="" && film==null) {
			return terminskaListaRepository.findAll(cenaEquals(cena).and(danProjekcijeEquals(danProjekcije)));
		}else if(pocetak!="" && cena=="" && danProjekcije=="" && film!=null) {
			return terminskaListaRepository.findAll(pocetakEquals(pocetak).and(filmEquals(film)));
		}else if(pocetak!="" && cena=="" && danProjekcije!="" && film==null) {
			return terminskaListaRepository.findAll(pocetakEquals(pocetak).and(danProjekcijeEquals(danProjekcije)));
		}else if(pocetak!="" && cena!="" && danProjekcije=="" && film==null) {
			return terminskaListaRepository.findAll(pocetakEquals(pocetak).and(cenaEquals(cena)));
		}else if(pocetak!="" && cena=="" && danProjekcije=="" && film==null) {
			return terminskaListaRepository.findAll(pocetakEquals(pocetak));
		}else if(pocetak=="" && cena!="" && danProjekcije=="" && film==null) {
			return terminskaListaRepository.findAll(cenaEquals(cena));
		}else if(pocetak=="" && cena=="" && danProjekcije!="" && film==null) {
			return terminskaListaRepository.findAll(danProjekcijeEquals(danProjekcije));
		}else if(pocetak=="" && cena=="" && danProjekcije=="" && film!=null) {
			return terminskaListaRepository.findAll(filmEquals(film));
		}
		return terminskaListaRepository.findAll(pocetakEquals(pocetak).or(cenaEquals(cena)).or(danProjekcijeEquals(danProjekcije)).or(filmEquals(film)));
	}
	public List<TerminskaLista> sortCenaKarte() {
		return terminskaListaRepository.findAllByOrderByCenaAsc();
	}
}
