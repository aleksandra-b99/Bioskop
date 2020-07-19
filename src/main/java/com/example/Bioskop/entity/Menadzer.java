package com.example.Bioskop.entity;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Menadzer extends Korisnik{
	public Menadzer() {
	}
	public Menadzer( String korisnicko_ime, String ime, String prezime, String lozinka, String kontakt_telefon,
			String e_adresa, Date datum_rodjenja, String uloga, boolean aktivan) {
		super(korisnicko_ime,ime,prezime,lozinka,kontakt_telefon,e_adresa,datum_rodjenja,uloga,aktivan);
	}
	//jedan menadzer moze da kontolise vise bioskopa
	@OneToMany( mappedBy = "menadzer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Bioskop> bioskopi=new ArrayList<>();
	
	public List<Bioskop> getBioskopi() {
		return bioskopi;
	}
	public void setBioskopi(List<Bioskop> bioskopi) {
		this.bioskopi = bioskopi;
	}
	
}
