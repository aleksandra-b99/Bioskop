package com.example.Bioskop.entity;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
@Entity
public class Administrator extends Korisnik{
	public Administrator(Long id, String korisnicko_ime, String ime, String prezime, String lozinka, String kontakt_telefon,
			String e_adresa, Date datum_rodjenja, String uloga, boolean aktivan) {
		super(id, e_adresa, e_adresa, e_adresa, e_adresa, e_adresa, e_adresa, datum_rodjenja, e_adresa, aktivan);
	}
	//administrataor upravlja celim sistemom
}
