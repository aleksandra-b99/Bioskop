package com.example.Bioskop.entity.dto;

import java.sql.Date;

import com.example.Bioskop.entity.Film;
import com.example.Bioskop.entity.Sala;

public class TerminskaListaDTO {
	private Long id;
	private String  danProjekcije;
	private String pocetak;
	private String cena;
	private int brojRezervacija;//ne  treba kod kreiranja tl
	private String nazivSale;//ne  treba kod kreiranja tl
	private String nazivFilma;
	private String zanrFilma;
	private int kapacitetSale;//ne  treba kod kreiranja tl
	private String trajanjeFilma;
	private String opisFilma;
	//////////////
	public String getTrajanjeFilma() {
		return trajanjeFilma;
	}
	public void setTrajanjeFilma(String trajanjeFilma) {
		this.trajanjeFilma = trajanjeFilma;
	}
	public String getOpisFilma() {
		return opisFilma;
	}
	public void setOpisFilma(String opisFilma) {
		this.opisFilma = opisFilma;
	}
	public Double getOcena() {
		return ocena;
	}
	public void setOcena(Double ocena) {
		this.ocena = ocena;
	}
	private Double ocena;
	
	
	
	public int getKapacitetSale() {
		return kapacitetSale;
	}
	public void setKapacitetSale(int kapacitetSale) {
		this.kapacitetSale = kapacitetSale;
	}
	public String getZanrFilma() {
		return zanrFilma;
	}
	public void setZanrFilma(String zanrFilma) {
		this.zanrFilma = zanrFilma;
	}
	public String getNazivSale() {
		return nazivSale;
	}
	public void setNazivSale(String nazivSale) {
		this.nazivSale = nazivSale;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNazivFilma() {
		return nazivFilma;
	}
	public void setNazivFilma(String nazivFilma) {
		this.nazivFilma = nazivFilma;
	}
	public String  getDanProjekcije() {
		return danProjekcije;
	}
	public void setDanProjekcije(String  danProjekcije) {
		this.danProjekcije = danProjekcije;
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
	public void setBrojRezervacija(int brojRezervacija) {
		this.brojRezervacija = brojRezervacija;
	}
	///////constructors/////

	public TerminskaListaDTO() {}
	public TerminskaListaDTO(Long id, String danProjekcije, String pocetak, String cena, int brojRezervacija,
			String nazivSale, String nazivFilma, String zanrFilma, int kapacitetSale, String trajanjeFilma,
			String opisFilma, Double ocena) {
		super();
		this.id = id;
		this.danProjekcije = danProjekcije;
		this.pocetak = pocetak;
		this.cena = cena;
		this.brojRezervacija = brojRezervacija;
		this.nazivSale = nazivSale;
		this.nazivFilma = nazivFilma;
		this.zanrFilma = zanrFilma;
		this.kapacitetSale = kapacitetSale;
		this.trajanjeFilma = trajanjeFilma;
		this.opisFilma = opisFilma;
		this.ocena = ocena;
	}
	
	
}
