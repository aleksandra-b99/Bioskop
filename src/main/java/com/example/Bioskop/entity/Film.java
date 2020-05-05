package com.example.Bioskop.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
@Entity
public class Film implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String naziv;
	@Column
	private String opis;
	@Column
	private String zanr;
	@Column
	private String trajanje;
	@Column
	private double srednja_ocena;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public String getZanr() {
		return zanr;
	}
	public void setZanr(String zanr) {
		this.zanr = zanr;
	}
	public String getTrajanje() {
		return trajanje;
	}
	public void setTrajanje(String trajanje) {
		this.trajanje = trajanje;
	}
	public double getOcena() {
		return srednja_ocena;
	}
	public void setOcena(double srednja_ocena) {
		this.srednja_ocena = srednja_ocena;
	}
	@Override
	public String toString() {
		return "Film [id=" + id + ", naziv=" + naziv + ", opis=" + opis + ", zanr=" + zanr + ", trajanje=" + trajanje
				+ ", srednja_ocena=" + srednja_ocena + "]";
	}
	
	
	public Film(Long id, String naziv, String opis, String zanr, String trajanje, double srednja_ocena) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
		this.zanr = zanr;
		this.trajanje = trajanje;
		this.srednja_ocena = srednja_ocena;
	}


	@ManyToMany(mappedBy="rezervisani_filmovi")
	private Set<Gledaoc> zaineresovani_korisnici=new HashSet<>();
	
	@ManyToMany ( mappedBy="filmovi_u_sali")
	private Set<Sala> sale=new HashSet<Sala>(); //vise filmova u jednoj sali*/
	
	@ManyToMany(mappedBy="filmovi")
	private Set<Bioskop> bioskopi=new HashSet<>();//u kojim bioskopima ce se prikazivati film
}
