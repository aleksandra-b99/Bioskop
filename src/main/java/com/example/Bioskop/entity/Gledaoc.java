package com.example.Bioskop.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Gledaoc extends Korisnik{
	
	@ManyToMany(fetch = FetchType.LAZY, cascade =CascadeType.PERSIST)
	/*@JoinTable(name = "rezervisaneTerminskeListe",
    joinColumns = @JoinColumn(name = "gledaoc_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "termisnkaLista_id", referencedColumnName = "id"))*/
	private List<TerminskaLista> rezervisaneTerminskeListe=new ArrayList<>();
	
	@OneToMany(mappedBy="gledaoc", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private List<Ocene_odgledanih_filmova> ocene_odgledanih_filmova=new ArrayList<>();
	
	@ManyToMany
	private List<Film> odgledaniFilmovi=new ArrayList<>();
	
	@ManyToMany
	private List<Film> sviOdgledaniFilmovi=new ArrayList<>();
	//////////get////////set
	
	public List<Ocene_odgledanih_filmova> getOcene_odgledanih_filmova() {
		return ocene_odgledanih_filmova;
	}
	public List<Film> getSviOdgledaniFilmovi() {
		return sviOdgledaniFilmovi;
	}
	public void setSviOdgledaniFilmovi(List<Film> sviOdgledaniFilmovi) {
		this.sviOdgledaniFilmovi = sviOdgledaniFilmovi;
	}
	public void setOcene_odgledanih_filmova(List<Ocene_odgledanih_filmova> ocene_odgledanih_filmova) {
		this.ocene_odgledanih_filmova = ocene_odgledanih_filmova;
	}
	public List<Film> getOdgledaniFilmovi() {
		return odgledaniFilmovi;
	}
	public void setOdgledaniFilmovi(List<Film> odgledaniFilmovi) {
		this.odgledaniFilmovi = odgledaniFilmovi;
	}
	public void setRezervisaneTerminskeListe(List<TerminskaLista> rezervisaneTerminskeListe) {
		this.rezervisaneTerminskeListe = rezervisaneTerminskeListe;
	}
	public List<TerminskaLista> getRezervisaneTerminskeListe() {
		return rezervisaneTerminskeListe;
	}
	public void removeTerminskaLista(TerminskaLista terminskaLista) {
		rezervisaneTerminskeListe.remove(terminskaLista);
		terminskaLista.getZaineresovaniKorisnici().remove(this);
	}
	////////////constructors///////////
	public Gledaoc( String korisnicko_ime, String ime, String prezime, String lozinka, String kontakt_telefon,
			String e_adresa, Date datum_rodjenja, String uloga, boolean aktivan) {
		super(korisnicko_ime,ime,prezime,lozinka,kontakt_telefon,e_adresa,datum_rodjenja,uloga,aktivan);
	}
	public Gledaoc() {}
	
}
