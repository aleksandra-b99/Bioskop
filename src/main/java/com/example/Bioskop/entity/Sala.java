package com.example.Bioskop.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
@Entity
public class Sala implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column
	private int kapacitet;
	@Column
	private String oznakaSale;
	//////get////////set///////
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getKapacitet() {
		return kapacitet;
	}
	public void setKapacitet(int kapacitet) {
		this.kapacitet = kapacitet;
	}
	public String getOznakaSale() {
		return oznakaSale;
	}
	public void setOznakaSale(String oznaka_sale) {
		this.oznakaSale = oznaka_sale;
	}
	public Bioskop getBioskop() {
		return bioskop;
	}
	public void setBioskop(Bioskop bioskop) {
		this.bioskop = bioskop;
	}
	public List<TerminskaLista> getTerminskaLista() {
		return termiskaLista;
	}
	public void setTerminskaLista(List<TerminskaLista> projekcije) {
		this.termiskaLista = projekcije;
	}
	//////////constructors///////////
	public Sala() {}
	
	public Sala(int kapacitet, String oznakaSale, Bioskop bioskop) {
		super();
		this.kapacitet = kapacitet;
		this.oznakaSale = oznakaSale;
		this.bioskop = bioskop;
	}

	//////////veze sa drugim klasama//////
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Bioskop bioskop;
	//ima vise sala u jednom bioskopu

	@ManyToMany ( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "filmovi_u_sali",
    joinColumns = @JoinColumn(name = "sala_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"))
	private Set<Film> filmovi_u_sali=new HashSet<Film>();//u jednoj sali ima vise filmova
	
	@OneToMany(mappedBy = "sala", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<TerminskaLista> termiskaLista=new ArrayList<>();
	
	
	
}
