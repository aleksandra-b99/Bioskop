package com.example.Bioskop.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Korisnik implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="")
	private String korisnickoIme;
	@Column
	private String ime;
	@Column
	private String prezime;
	@Column
	private String lozinka;
	@Column
	private String kontaktTelefon;
	@Column
	private String eadresa;
	@Column
	private Date datumRodjenja;
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
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnicko_ime) {
		this.korisnickoIme = korisnicko_ime;
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
	public String getKontaktTelefon() {
		return kontaktTelefon;
	}
	public void setKontaktTelefon(String kontakt_telefon) {
		this.kontaktTelefon = kontakt_telefon;
	}
	public String getEadresa() {
		return eadresa;
	}
	public void setEadresa(String e_adresa) {
		this.eadresa = e_adresa;
	}
	public Date getDatumRodjenja() {
		return datumRodjenja;
	}
	public void setDatumRodjenja(Date datum_rodjenja) {
		this.datumRodjenja = datum_rodjenja;
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
		return "Gledaoc [id=" + id + ", korisnicko_ime=" + korisnickoIme + ", ime=" + ime + ", prezime=" + prezime
				+ ", lozinka=" + lozinka + ", kontakt_telefon=" + kontaktTelefon + ", e_adresa=" + eadresa
				+ ", datum_rodjenja=" + datumRodjenja + ", uloga=" + uloga + ", aktivan=" + aktivan + "]";
	}
	public Korisnik() {}
	public Korisnik(Long id, String korisnicko_ime, String ime, String prezime, String lozinka, String kontakt_telefon,
			String e_adresa, Date datum_rodjenja, String uloga, boolean aktivan) {
		super();
		this.id = id;
		this.korisnickoIme = korisnicko_ime;
		this.ime = ime;
		this.prezime = prezime;
		this.lozinka = lozinka;
		this.kontaktTelefon = kontakt_telefon;
		this.eadresa = e_adresa;
		this.datumRodjenja = datum_rodjenja;
		this.uloga = uloga;
		this.aktivan = aktivan;
	}
	
	
	
}
