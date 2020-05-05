package com.example.Bioskop.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

@MappedSuperclass
public class Korisnik implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String korisnicko_ime;
	@Column
	private String ime;
	@Column
	private String prezime;
	@Column
	private String lozinka;
	@Column
	private String kontakt_telefon;
	@Column
	private String e_adresa;
	@Column
	private Date datum_rodjenja;
	@Column
	private String uloga;
	@Column
	private boolean aktivan;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getKorisnicko_ime() {
		return korisnicko_ime;
	}
	public void setKorisnicko_ime(String korisnicko_ime) {
		this.korisnicko_ime = korisnicko_ime;
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
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	public String getKontakt_telefon() {
		return kontakt_telefon;
	}
	public void setKontakt_telefon(String kontakt_telefon) {
		this.kontakt_telefon = kontakt_telefon;
	}
	public String getE_adresa() {
		return e_adresa;
	}
	public void setE_adresa(String e_adresa) {
		this.e_adresa = e_adresa;
	}
	public Date getDatum_rodjenja() {
		return datum_rodjenja;
	}
	public void setDatum_rodjenja(Date datum_rodjenja) {
		this.datum_rodjenja = datum_rodjenja;
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
	@Override
	public String toString() {
		return "Gledaoc [id=" + id + ", korisnicko_ime=" + korisnicko_ime + ", ime=" + ime + ", prezime=" + prezime
				+ ", lozinka=" + lozinka + ", kontakt_telefon=" + kontakt_telefon + ", e_adresa=" + e_adresa
				+ ", datum_rodjenja=" + datum_rodjenja + ", uloga=" + uloga + ", aktivan=" + aktivan + "]";
	}
	
	
	
}
