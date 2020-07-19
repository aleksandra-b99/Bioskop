package com.example.Bioskop.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.Bioskop.entity.Film;
import com.example.Bioskop.entity.Gledaoc;
import com.example.Bioskop.entity.TerminskaLista;

public interface TerminskaListaRepository extends JpaRepository<TerminskaLista, Long>,JpaSpecificationExecutor<TerminskaLista>{
	List<TerminskaLista> findAllByFilm(Film film);
	TerminskaLista findByNazivAndZanr(String naziv, String zanr);
	TerminskaLista findByNaziv(String naziv);
	List<TerminskaLista> findAllByFilmId(Long id);
	TerminskaLista findByPocetakAndDanProjekcije(String pocetak, Date danProjekcije);
	TerminskaLista findByPocetak(String pocetak);
	List<TerminskaLista> findAllByOrderByNaziv();
	List<TerminskaLista> findAllByOrderByCenaAsc();
}