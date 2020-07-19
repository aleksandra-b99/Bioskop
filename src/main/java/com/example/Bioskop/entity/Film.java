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
	@Column(nullable=true)
	private Double ocena;
	////////////get//////////////set//////////
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
	public Double getOcena() {
		return ocena;
	}
	public void setOcena(Double srednja_ocena) {
		this.ocena = srednja_ocena;
	}
	public List<Sala> getSale() {
		return sale;
	}
	public void setSale(List<Sala> sale) {
		this.sale = sale;
	}
	public List<TerminskaLista> getTerminskeListe() {
		return terminskeListe;
	}
	public void setTerminskeListe(List<TerminskaLista> terminskeListe) {
		this.terminskeListe = terminskeListe;
	}
	////constructors/////////
	public Film() {}
	
	public Film(String naziv, String opis, String zanr, String trajanje, Double srednja_ocena) {
		super();
		this.naziv = naziv;
		this.opis = opis;
		this.zanr = zanr;
		this.trajanje = trajanje;
		this.ocena = srednja_ocena;
	}
	///////dodatne veze///////////////
	@ManyToMany ( mappedBy="filmovi_u_sali")
	private List<Sala> sale=new ArrayList<>(); //vise filmova u jednoj sali*/
	
	@OneToMany(mappedBy = "film", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<TerminskaLista> terminskeListe; 
	
	@ManyToMany(mappedBy="filmovi")
	private Set<Bioskop> bioskopi=new HashSet<>();//u kojim bioskopima ce se prikazivati film
	
	//@ManyToMany(mappedBy="rezervisaniFilmovi")
	//private List<Gledaoc> zaineresovaniKorisnici=new ArrayList<>();
	
	@ManyToMany
	private List<Gledaoc> gledaoci=new ArrayList<>();
}
