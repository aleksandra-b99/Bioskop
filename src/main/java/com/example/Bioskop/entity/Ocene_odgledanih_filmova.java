package com.example.Bioskop.entity;

import java.io.Serializable;

import javax.persistence.*;
@Entity
public class Ocene_odgledanih_filmova implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String naziv;
	
	@Column(nullable=true)
	private int ocena;
	
	@ManyToOne
	private Gledaoc gledaoc;

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

	public int getOcena() {
		return ocena;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}

	public Gledaoc getGledaoc() {
		return gledaoc;
	}

	public void setGledaoc(Gledaoc gledaoc) {
		this.gledaoc = gledaoc;
	}

	public Ocene_odgledanih_filmova(String naziv, int ocena, Gledaoc gledaoc) {
		super();
		this.naziv = naziv;
		this.ocena = ocena;
		this.gledaoc = gledaoc;
	}
	public Ocene_odgledanih_filmova() {}
	
	
	
}
