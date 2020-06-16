package com.example.Bioskop.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Gledaoc extends Korisnik{
	public Gledaoc(Long id, String korisnicko_ime, String ime, String prezime, String lozinka, String kontakt_telefon,
			String e_adresa, Date datum_rodjenja, String uloga, boolean aktivan) {
		super(id, e_adresa, e_adresa, e_adresa, e_adresa, e_adresa, e_adresa, datum_rodjenja, e_adresa, aktivan);
	}
	@ManyToMany
	@JoinTable(name = "rezervisani_filmovi",
    joinColumns = @JoinColumn(name = "gledaoc_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"))
	private Set<Film> rezervisani_filmovi=new HashSet<>();
}
