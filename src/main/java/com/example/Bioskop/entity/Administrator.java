package com.example.Bioskop.entity;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
@Entity
public class Administrator extends Korisnik{
	public Administrator( String korisnicko_ime, String ime, String prezime, String lozinka, String kontakt_telefon,
			String e_adresa, Date datum_rodjenja, String uloga, boolean aktivan) {
		super(korisnicko_ime,ime,prezime,lozinka,kontakt_telefon,e_adresa,datum_rodjenja,uloga,aktivan);
	}
	public Administrator() {}
	//administrataor upravlja celim sistemom
	
	
	
}
