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
	private String brojCentrale;
	@Column
	private String email;
	////////////get/////set
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
	public String getBrojCentrale() {
		return brojCentrale;
	}
	public void setBrojCentrale(String broj_centrale) {
		this.brojCentrale = broj_centrale;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String e_mail) {
		this.email = e_mail;
	}
	@Override
	public String toString() {
		return "Bioskop [id=" + id + ", naziv=" + naziv + ", adresa=" + adresa + ", broj_centrale=" + brojCentrale
				+ ", e_mail=" + email + "]";
	}
	
	
	
	public Bioskop(String naziv, String adresa, String brojCentrale, String email, Menadzer menadzer) {
		super();
		this.naziv = naziv;
		this.adresa = adresa;
		this.brojCentrale = brojCentrale;
		this.email = email;
		this.menadzer = menadzer;
	}
	public Bioskop() {}
	
	

	@ManyToMany
	@JoinTable(name = "filmovi_u_bioskopu",
    joinColumns = @JoinColumn(name = "bioskop_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"))
	private Set<Film> filmovi=new HashSet<>();
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Menadzer menadzer;
	//jedan bioskop moze da ima vise menadzera
	
	public Menadzer getMenadzeri() {
		return menadzer;
	}
	public void setMenadzeri(Menadzer menadzer) {
		this.menadzer = menadzer;
	}

	@OneToMany(mappedBy = "bioskop", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Sala> sale = new ArrayList<>();//u bioskopu ima vise sala
	
	public List<Sala> getSale() {
		return sale;
	}
	public void setSale(List<Sala> sale) {
		this.sale = sale;
	}
	
	
	//dnevni raspored
	//@OneToMany
	//private Set<Terminska_lista> terminska_lista=new HashSet<>();
	
}
