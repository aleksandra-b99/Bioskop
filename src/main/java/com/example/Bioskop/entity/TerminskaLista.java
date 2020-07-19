package com.example.Bioskop.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
@Entity
public class TerminskaLista implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column
	private String danProjekcije;
	@Column
	private String pocetak;
	@Column
	private String cena;
	@Column
	private int brojRezervacija;
	private String naziv;
	private String zanr;
	

	/////////constructors////////
	public TerminskaLista () {}

	public TerminskaLista( String danProjekcije, String pocetak, String cena, int brojRezervacija, Film film,
			Sala sala) {
		super();
		this.danProjekcije = danProjekcije;
		this.pocetak = pocetak;
		this.cena = cena;
		this.brojRezervacija = brojRezervacija;
		this.film = film;
		this.sala = sala;
	}

////////get///////////set/
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String  getDanProjekcije() {
		return danProjekcije;
	}
	public void setDanProjekcije(String  dan_projekcije) {
		this.danProjekcije = dan_projekcije;
	}
	public String getPocetak() {
		return pocetak;
	}
	public void setPocetak(String pocetak) {
		this.pocetak = pocetak;
	}
	public String getCena() {
		return cena;
	}
	public void setCena(String cena) {
		this.cena = cena;
	}
	public int getBrojRezervacija() {
		return brojRezervacija;
	}
	public void setBrojRezervacija(int broj_rezervacija) {
		this.brojRezervacija = broj_rezervacija;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getZanr() {
		return zanr;
	}

	public void setZanr(String zanr) {
		this.zanr = zanr;
	}
	public List<Gledaoc> getZaineresovaniKorisnici() {
		return zaineresovaniKorisnici;
	}

	public void setZaineresovaniKorisnici(List<Gledaoc> zaineresovaniKorisnici) {
		this.zaineresovaniKorisnici = zaineresovaniKorisnici;
	}
	
	///////dodatne veze/////////
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Film film;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Sala sala;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private List<Gledaoc> zaineresovaniKorisnici=new ArrayList<>();

}
