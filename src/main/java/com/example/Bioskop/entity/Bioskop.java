package com.example.Bioskop.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.*;

@Entity
public class Bioskop implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String naziv;
	@Column
	private String adresa;
	@Column
	private String broj_centrale;
	@Column
	private String e_mail;
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
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getBroj_centrale() {
		return broj_centrale;
	}
	public void setBroj_centrale(String broj_centrale) {
		this.broj_centrale = broj_centrale;
	}
	public String getE_mail() {
		return e_mail;
	}
	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}
	@Override
	public String toString() {
		return "Bioskop [id=" + id + ", naziv=" + naziv + ", adresa=" + adresa + ", broj_centrale=" + broj_centrale
				+ ", e_mail=" + e_mail + "]";
	}

	public Bioskop(Long id, String naziv, String adresa, String broj_centrale, String e_mail) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.adresa = adresa;
		this.broj_centrale = broj_centrale;
		this.e_mail = e_mail;
	}
	public Menadzer getMenadzer() {
		return menadzer;
	}
	public void setMenadzer(Menadzer menadzer) {
		this.menadzer = menadzer;
	}
	@ManyToMany
	@JoinTable(name = "filmovi_u_bioskopu",
    joinColumns = @JoinColumn(name = "bioskop_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"))
	private Set<Film> filmovi=new HashSet<>();
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Menadzer menadzer;
	//vise bioskopa kontrolise jedan menadzer
	
	@OneToMany(mappedBy = "bioskop", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Sala> sale = new HashSet<>();//u bioskopu ima vise sala
	
	
	//dnevni raspored
	//@OneToMany
	//private Set<Terminska_lista> terminska_lista=new HashSet<>();
	
}
