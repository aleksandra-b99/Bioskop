package com.example.Bioskop.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
@Entity
public class Terminska_lista implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column
	private String naziv_filma;
	@Column
	private String dan_projekcije;
	@Column
	private String pocetak;
	@Column
	private int cena;
	@Column
	private int broj_rezervacija;
	
	public Terminska_lista(Long id, String naziv_filma, String dan_projekcije, String pocetak, int cena,
			int broj_rezervacija, Sala sala) {
		super();
		this.id = id;
		this.naziv_filma = naziv_filma;
		this.dan_projekcije = dan_projekcije;
		this.pocetak = pocetak;
		this.cena = cena;
		this.broj_rezervacija = broj_rezervacija;
		this.sala = sala;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNaziv_filma() {
		return naziv_filma;
	}
	public void setNaziv_filma(String naziv_filma) {
		this.naziv_filma = naziv_filma;
	}
	public String getDan_projekcije() {
		return dan_projekcije;
	}
	public void setDan_projekcije(String dan_projekcije) {
		this.dan_projekcije = dan_projekcije;
	}
	public String getPocetak() {
		return pocetak;
	}
	public void setPocetak(String pocetak) {
		this.pocetak = pocetak;
	}
	public int getCena() {
		return cena;
	}
	public void setCena(int cena) {
		this.cena = cena;
	}
	public int getBroj_rezervacija() {
		return broj_rezervacija;
	}
	public void setBroj_rezervacija(int broj_rezervacija) {
		this.broj_rezervacija = broj_rezervacija;
	}
	@Override
	public String toString() {
		return "Raspored_sala [id=" + id + ", naziv_filma=" + naziv_filma + ", dan_projekcije=" + dan_projekcije
				+ ", pocetak=" + pocetak + ", cena=" + cena + ", broj_rezervacija=" + broj_rezervacija + "]";
	}
	@ManyToOne
	private Sala sala;
	
}
