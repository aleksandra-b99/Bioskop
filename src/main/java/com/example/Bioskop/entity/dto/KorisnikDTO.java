package com.example.Bioskop.entity.dto;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class KorisnikDTO {
	private Long id;
	private String ime;
	private String prezime;
	private String korisnickoIme;
	private String lozinka;
	private String kontaktTelefon;
	private String eadresa;
	private Date datumRodjenja;
	private String uloga;
	private boolean aktivan;
	
	public KorisnikDTO() {}
	
	public KorisnikDTO(Long id, String ime, String prezime, String korisnickoIme, String lozinka, String kontaktTelefon,
			String eadresa, Date datumRodjenja, String uloga, boolean aktivan) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.kontaktTelefon = kontaktTelefon;
		this.eadresa = eadresa;
		this.datumRodjenja = datumRodjenja;
		this.uloga = uloga;
		this.aktivan = aktivan;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	public String getKontaktTelefon() {
		return kontaktTelefon;
	}
	public void setKontaktTelefon(String kontaktTelefon) {
		this.kontaktTelefon = kontaktTelefon;
	}
	public String getEadresa() {
		return eadresa;
	}
	public void setEadresa(String eadresa) {
		this.eadresa = eadresa;
	}
	public Date getDatumRodjenja() {
		return datumRodjenja;
	}
	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	public String getUloga() {
		return uloga;
	}
	public void setUloga(String uloga) {
		this.uloga = uloga;
	}
	public boolean isAktivan() {
		return aktivan;
	}
	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}
}
